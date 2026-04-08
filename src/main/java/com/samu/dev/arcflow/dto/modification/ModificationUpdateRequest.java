package com.samu.dev.arcflow.dto.modification;

import java.math.BigDecimal;
import java.time.LocalTime;

public record ModificationUpdateRequest(
        String title,
        String description,
        String status,
        BigDecimal impactHours,
        BigDecimal extraCost
) {
}
