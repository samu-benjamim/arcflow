package com.samu.dev.arcflow.dto.client;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientUpdateRequest(
        String name,
        String email,
        String phone,
        String cpfCnpj,
        String address
) {}
