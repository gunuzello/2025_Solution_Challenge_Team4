package com.example.doran_backend.dto;

import lombok.Getter;

@Getter
public class InterviewEndRequest {

    private String sessionId;
    private Long userId;
    private String endReason; // USER_EXIT 등
}