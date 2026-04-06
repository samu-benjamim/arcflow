package com.samu.dev.arcflow.dto.projectphase;


import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectPhaseUpdateRequest(
        String status,
        Integer order,
        BigDecimal completionPct,
        LocalDate startDate,
        LocalDate deadline
) {}
