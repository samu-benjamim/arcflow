package com.samu.dev.arcflow.dto.projectphase;


import java.time.LocalDateTime;

public record ProjectPhaseCreateRequest(
        Long projectId,
        String type,
        String status,
        Integer order,
        LocalDateTime startDate,
        LocalDateTime deadline
) {}
