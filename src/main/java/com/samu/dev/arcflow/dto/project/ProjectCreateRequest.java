package com.samu.dev.arcflow.dto.project;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectCreateRequest(
        String name,
        String type,
        String status,
        BigDecimal totalAreaM2,
        BigDecimal contractValue,
        LocalDate startDate,
        LocalDate deadline
) {}
