package com.samu.dev.arcflow.dto.document;

public record DocumentCreateRequest(
        Long phaseId,
        String name,
        String documentType,
        String documentCode,
        String fileUrl
) {}
