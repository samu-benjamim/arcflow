package com.samu.dev.arcflow.dto.clientapproval;

import java.time.LocalDate;

public record ClientApprovalCreateRequest (
        Long phaseId,
        Long documentId,
        LocalDate requestedAt
) {}
