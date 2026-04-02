package com.samu.dev.arcflow.dto.project;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectUpdateRequest(
        String name,
        String status,
        Long userId,
        BigDecimal totalAreaM2,
        BigDecimal contractValue,
        LocalDate startDate,
        LocalDate deadline
) {}
