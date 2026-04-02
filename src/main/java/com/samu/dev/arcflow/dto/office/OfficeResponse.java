package com.samu.dev.arcflow.dto.office;

import java.time.LocalDate;

public record OfficeResponse(
        Long id,
        String name,
        String cnpj,
        String email,
        LocalDate createdAt
){}
