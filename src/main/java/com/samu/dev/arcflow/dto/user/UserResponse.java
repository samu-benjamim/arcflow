package com.samu.dev.arcflow.dto.user;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        boolean active
) {}
