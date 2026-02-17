package com.example.doran_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InterviewTurnRequest {

    private String sessionId;
    private Long userId;
    private String requestId;
    private Input input;
    private Context context;
    private String clientTs; // ISO8601 (문자열로 받기)

    @Getter
    @AllArgsConstructor
    public static class Input {
        private String mode;     // "TEXT"
        private String userText; // 사용자 발화
    }

    @Getter
    @AllArgsConstructor
    public static class Context {
        private String speechLevel;     // "HONORIFIC" 등
        private String ageGroup;        // "SENIOR_70S" 등
        private List<String> coreValues; // ["가족", ...]
        private String extraValue;      // "정" 등
    }
}