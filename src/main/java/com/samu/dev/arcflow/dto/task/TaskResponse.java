package com.samu.dev.arcflow.dto.task;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        Long phaseId,
        Long userId,
        Integer order,
        String status,
        BigDecimal estimatedHours,
        LocalDate deadline,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
