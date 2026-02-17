package com.example.doran_backend.dto;

import lombok.Getter;

@Getter
public class InterviewStartRequest {

    private Long userId;
    private String clientTs; // ISO8601 형식 문자열
}