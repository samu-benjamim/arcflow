package com.samu.dev.arcflow.dto.modification;

import java.math.BigDecimal;
import java.time.LocalTime;

public record ModificationCreateRequest(
        Long projectId,
        String title,
        String description,
        String origin,
        LocalTime impactHours,
        BigDecimal extraCost
) {
}
