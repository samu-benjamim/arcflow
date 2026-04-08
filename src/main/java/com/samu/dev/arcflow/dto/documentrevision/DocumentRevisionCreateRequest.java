package com.samu.dev.arcflow.dto.documentrevision;

public record DocumentRevisionCreateRequest(
        Long documentId,
        Long userId,
        String revisao,
        String fileUrl,
        String changeDescription
        ) {
}
