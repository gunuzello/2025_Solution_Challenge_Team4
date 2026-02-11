package com.example.doran_backend.repository;

import com.example.doran_backend.entity.TurnLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurnLogRepository extends JpaRepository<TurnLog, String> {
    Optional<TurnLog> findBySessionIdAndRequestId(String sessionId, String requestId);
}