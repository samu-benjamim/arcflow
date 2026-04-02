package com.samu.dev.arcflow.dto.project;

import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.model.Office;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectSummaryResponse(
        Long id,
        String name,
        String type,
        String status,
        Long userId,
        BigDecimal totalAreaM2,
        BigDecimal contractValue,
        LocalDate startDate,
        LocalDate deadline
) {}
