package com.samu.dev.arcflow.dto.document;

public record DocumentUpdateRequest(
        String name,
        String documentCode,
        String fileUrl
) {}
