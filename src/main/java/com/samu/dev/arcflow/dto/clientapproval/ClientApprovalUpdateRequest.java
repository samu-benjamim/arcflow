package com.samu.dev.arcflow.dto.clientapproval;

import java.time.LocalDate;

public record ClientApprovalUpdateRequest(
        String status,
        LocalDate respondedAt,
        String comment
) {}
