package com.example.doran_backend.controller;

import com.example.doran_backend.dto.StartInterviewRequest;
import com.example.doran_backend.dto.StartInterviewResponse;
import com.example.doran_backend.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/start")
    public StartInterviewResponse startInterview(
            @RequestBody StartInterviewRequest request
    ) {
        return interviewService.startInterview(request);
    }
}