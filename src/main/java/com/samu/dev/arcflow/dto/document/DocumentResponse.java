package com.samu.dev.arcflow.dto.document;

public record DocumentResponse(
        Long id,
        Long phaseId,
        String name,
        String documentType,
        String documentCode,
        String fileUrl
) {}
