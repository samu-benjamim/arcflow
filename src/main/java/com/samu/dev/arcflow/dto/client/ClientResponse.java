package com.samu.dev.arcflow.dto.client;

public record ClientResponse(
        Long id,
        String name,
        String email,
        String phone,
        String cpfCnpj,
        String address
) {}
