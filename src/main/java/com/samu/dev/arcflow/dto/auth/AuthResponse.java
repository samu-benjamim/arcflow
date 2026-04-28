package com.samu.dev.arcflow.dto.auth;

public record AuthResponse(
        String token,
        String name,
        String email,
        String role,
        Long officeId
) {}
