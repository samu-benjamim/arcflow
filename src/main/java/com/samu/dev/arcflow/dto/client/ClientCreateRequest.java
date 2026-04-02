package com.samu.dev.arcflow.dto.client;

public record ClientCreateRequest(
        String name,
        String email,
        String phone,
        String cpfCnpj,
        String address
) {}
