package com.samu.dev.arcflow.dto.documentrevision;

public record DocumentRevisionResponse(
        Long id,
        Long documentId,
        Long userId,
        Character revisao,
        String fileUrl,
        String changeDescription
        ) {
}
