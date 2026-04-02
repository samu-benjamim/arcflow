package com.samu.dev.arcflow.dto.projectphase;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProjectPhaseUpdateRequest(
        String status,
        Integer order,
        BigDecimal completionPct,
        LocalDateTime startDate,
        LocalDateTime deadline
) {}
