package com.samu.dev.arcflow.dto.task;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TaskUpdateRequest(
        String title,
        String description,
        Long userId,
        Integer order,
        String status,
        BigDecimal estimatedHours,
        LocalDate deadline
) {}
