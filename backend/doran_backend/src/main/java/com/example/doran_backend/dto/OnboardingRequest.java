package com.example.doran_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OnboardingRequest {
    private String userTitle;        // 1~10
    private String ageGroup;         // SENIOR_60S ...
    private String speechLevel;      // HONORIFIC / CASUAL
    private Boolean hasChildren;     // true/false
    private String happiestMoment;   // 0~200 (optional)
    private List<String> coreValues; // optional, max 5
    private String extraValue;       // 1~7 (optional)
}