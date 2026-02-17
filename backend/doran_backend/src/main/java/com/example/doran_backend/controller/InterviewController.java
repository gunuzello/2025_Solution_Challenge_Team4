package com.example.doran_backend.controller;

import com.example.doran_backend.dto.InterviewEndRequest;
import com.example.doran_backend.dto.InterviewEndResponse;
import com.example.doran_backend.dto.InterviewStartRequest;
import com.example.doran_backend.dto.InterviewStartResponse;
import com.example.doran_backend.dto.InterviewTurnRequest;
import com.example.doran_backend.dto.InterviewTurnResponse;
import com.example.doran_backend.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    // POST /interview/start
    @PostMapping("/start")
    public InterviewStartResponse start(@RequestBody InterviewStartRequest request) {
        return interviewService.startInterview(request);
    }

    // POST /interview/end
    @PostMapping("/end")
    public InterviewEndResponse end(@RequestBody InterviewEndRequest request) {
        return interviewService.endInterview(request);
    }

    // POST /interview/turn
    @PostMapping("/turn")
    public InterviewTurnResponse turn(@RequestBody InterviewTurnRequest request) {
        return interviewService.turn(request);
    }
}