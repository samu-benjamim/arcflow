package com.samu.dev.arcflow.dto.modification;

import java.math.BigDecimal;
import java.time.LocalTime;

public record ModificationResponse(
        Long id,
        Long projectId,
        Long phaseId,
        Long userId,
        String title,
        String description,
        String origin,
        LocalTime impactHours,
        BigDecimal extraCost,
        String status
) {
}
