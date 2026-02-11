package com.example.doran_backend.repository;

import com.example.doran_backend.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession, String> {
}