package com.samu.dev.arcflow.dto.timeentry;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TimeEntryResponse(
        Long id,
        Long taskId,
        String taskTitle,
        Long userId,
        String userName,
        BigDecimal hoursWorked,
        LocalDate date,
        String description
) {}
