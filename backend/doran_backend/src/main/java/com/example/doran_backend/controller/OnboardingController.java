package com.example.doran_backend.controller;

import com.example.doran_backend.dto.OnboardingRequest;
import com.example.doran_backend.dto.ProfileResponse;
import com.example.doran_backend.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class OnboardingController {

    private final OnboardingService onboardingService;

    // POST /users/onboarding?userId=1
    @PostMapping("/onboarding")
    public ResponseEntity<Void> saveOnboarding(
            @RequestParam Long userId,
            @RequestBody OnboardingRequest request
    ) {
        onboardingService.saveOnboarding(userId, request);
        return ResponseEntity.ok().build();
    }

    // GET /users/profile?userId=1
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@RequestParam Long userId) {
        ProfileResponse response = onboardingService.getProfile(userId);
        return ResponseEntity.ok(response);
    }
}