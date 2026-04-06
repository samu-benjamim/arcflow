package com.samu.dev.arcflow.dto.projectphase;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProjectPhaseResponse(
        Long id,
        String type,
        String status,
        Integer order,
        BigDecimal completionPct,
        LocalDate startDate,
        LocalDate deadline
) {}
