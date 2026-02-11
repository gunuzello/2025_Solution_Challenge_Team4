package com.example.doran_backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StartInterviewResponse {

    private String sessionId;
}