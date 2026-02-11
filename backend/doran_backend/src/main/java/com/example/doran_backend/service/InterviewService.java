package com.example.doran_backend.service;

import com.example.doran_backend.dto.StartInterviewRequest;
import com.example.doran_backend.dto.StartInterviewResponse;
import com.example.doran_backend.entity.InterviewSession;
import com.example.doran_backend.repository.InterviewSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewSessionRepository interviewSessionRepository;

    public StartInterviewResponse startInterview(StartInterviewRequest request) {

        String sessionId = UUID.randomUUID().toString();

        InterviewSession session = InterviewSession.builder()
                .sessionId(sessionId)
                .userId(request.getUserId())
                .startedAt(LocalDateTime.now())
                .status("ACTIVE")
                .build();

        interviewSessionRepository.save(session);

        return StartInterviewResponse.builder()
                .sessionId(sessionId)
                .build();
    }
}