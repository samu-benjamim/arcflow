package com.samu.dev.arcflow.dto.projectphase;


import java.time.LocalDate;


public record ProjectPhaseCreateRequest(
        Long projectId,
        String type,
        String status,
        Integer order,
        LocalDate startDate,
        LocalDate deadline
) {}
