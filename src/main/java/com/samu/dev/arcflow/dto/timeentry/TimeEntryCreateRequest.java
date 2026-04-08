package com.samu.dev.arcflow.dto.timeentry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TimeEntryCreateRequest(
        Long taskId,
        BigDecimal hoursWorked,
        LocalDateTime date,
        String description
) {}
