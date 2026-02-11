package com.example.doran_backend.dto;

import lombok.Getter;

@Getter
public class StartInterviewRequest {

    private Long userId;
    private String clientTs; // ISO8601
}