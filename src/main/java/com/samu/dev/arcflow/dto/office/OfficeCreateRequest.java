package com.samu.dev.arcflow.dto.office;

public record OfficeCreateRequest(
        String name,
        String cnpj,
        String email
){
}
