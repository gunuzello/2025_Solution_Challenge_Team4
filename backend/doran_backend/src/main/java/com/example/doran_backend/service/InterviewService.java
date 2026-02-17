package com.example.doran_backend.service;

import com.example.doran_backend.dto.*;
import com.example.doran_backend.entity.InterviewSession;
import com.example.doran_backend.entity.TurnLog;
import com.example.doran_backend.repository.InterviewSessionRepository;
import com.example.doran_backend.repository.TurnLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewSessionRepository interviewSessionRepository;
    private final TurnLogRepository turnLogRepository; // turn() 저장/멱등성용

    // -------------------------
    // 1) 인터뷰 시작
    // -------------------------
    @Transactional
    public InterviewStartResponse startInterview(InterviewStartRequest request) {
        String sessionId = UUID.randomUUID().toString();

        InterviewSession session = InterviewSession.builder()
                .sessionId(sessionId)
                .userId(request.getUserId())
                .startedAt(LocalDateTime.now())
                .status("ACTIVE")
                .build();

        interviewSessionRepository.save(session);

        return new InterviewStartResponse(sessionId);
    }

    // -------------------------
    // 2) 인터뷰 종료
    // -------------------------
    @Transactional
    public InterviewEndResponse endInterview(InterviewEndRequest request) {
        InterviewSession session = interviewSessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new IllegalArgumentException("세션을 찾을 수 없습니다. sessionId=" + request.getSessionId()));

        if (!session.getUserId().equals(request.getUserId())) {
            throw new IllegalArgumentException("세션 접근 권한이 없습니다. userId=" + request.getUserId());
        }

        // 멱등성: 이미 ENDED면 그대로 반환
        if ("ENDED".equals(session.getStatus())) {
            return new InterviewEndResponse(session.getSessionId(), session.getStatus());
        }

        session.end(request.getEndReason());
        interviewSessionRepository.save(session);

        return new InterviewEndResponse(session.getSessionId(), session.getStatus());
    }

    // -------------------------
    // 3) 인터뷰 턴 (HARD CONTRACT 핵심)
    // -------------------------
    @Transactional
    public InterviewTurnResponse turn(InterviewTurnRequest request) {

        // (1) 세션 존재/ACTIVE 확인
        InterviewSession session = interviewSessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new IllegalArgumentException("세션을 찾을 수 없습니다. sessionId=" + request.getSessionId()));

        if (!session.getUserId().equals(request.getUserId())) {
            throw new IllegalArgumentException("세션 접근 권한이 없습니다. userId=" + request.getUserId());
        }

        if (!"ACTIVE".equals(session.getStatus())) {
            throw new IllegalArgumentException("종료된 세션입니다. sessionId=" + request.getSessionId());
        }

        // (2) userText 공백 체크 (HARD CONTRACT)
        String userText = trim(request.getInput().getUserText());
        if (userText == null) {
            throw new IllegalArgumentException("userText는 비어있을 수 없습니다.");
        }

        // (3) 멱등성: sessionId + requestId로 기존 TurnLog 있으면 그대로 반환
        TurnLog existing = turnLogRepository
                .findBySessionIdAndRequestId(request.getSessionId(), request.getRequestId())
                .orElse(null);

        if (existing != null) {
            InterviewTurnResponse.Output output =
                    new InterviewTurnResponse.Output(existing.getReply(), existing.getQuestion());

            InterviewTurnResponse.Meta meta =
                    new InterviewTurnResponse.Meta(existing.getModel(), existing.getPromptVersion(), existing.getLatencyMs());

            return new InterviewTurnResponse(existing.getSessionId(), existing.getTurnId(), output, meta);
        }

        // (4) 아직은 AI 호출 대신 더미 응답 (HARD CONTRACT 준수)
        String reply = "말씀해주셔서 감사합니다.";
        String question = "그때 어떤 기분이셨나요?"; // 반드시 ? 포함

        // (5) TurnLog 저장
        String turnId = UUID.randomUUID().toString();
        long latencyMs = 0L;

        TurnLog log = TurnLog.builder()
                .sessionId(request.getSessionId())
                .turnId(turnId)
                .userId(request.getUserId())
                .requestId(request.getRequestId())
                .ts(LocalDateTime.now()) // ✅ clientTs 파싱 안하고 "현재시간"으로 박아서 에러 제거
                .inputMode(request.getInput().getMode())
                .userText(userText)
                .reply(reply)
                .question(question)
                .rawModelOutput(null)
                .promptVersion("interview_v1")
                .model("dummy")
                .latencyMs(latencyMs)
                .build();

        turnLogRepository.save(log);

        // (6) 응답 반환
        InterviewTurnResponse.Output output = new InterviewTurnResponse.Output(reply, question);
        InterviewTurnResponse.Meta meta = new InterviewTurnResponse.Meta("dummy", "interview_v1", latencyMs);

        return new InterviewTurnResponse(request.getSessionId(), turnId, output, meta);
    }

    // -------------------------
    // helper
    // -------------------------
    private String trim(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}