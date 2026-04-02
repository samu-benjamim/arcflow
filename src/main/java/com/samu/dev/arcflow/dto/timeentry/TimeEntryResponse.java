package com.samu.dev.arcflow.dto.timeentry;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record TimeEntryResponse(
        Long id,
        Long taskId,
        LocalTime hoursWorked,
        LocalDateTime date,
        String description
) {}
