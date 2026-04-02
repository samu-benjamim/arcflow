package com.samu.dev.arcflow.dto.clientapproval;

import java.time.LocalDate;

public record ClientApprovalResponse(
        Long id,
        Long phaseId,
        String status,
        LocalDate resquesteAt,
        LocalDate respondedAt,
        String comment
) {}
