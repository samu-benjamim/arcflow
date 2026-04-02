package com.samu.dev.arcflow.dto.task;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TaskCreateRequest(
        String title,
        String description,
        Long phaseId,
        Integer order,
        BigDecimal estimatedHours,
        LocalDate deadline
) {}
