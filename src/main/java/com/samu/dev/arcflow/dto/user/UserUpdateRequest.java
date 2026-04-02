package com.samu.dev.arcflow.dto.user;

public record UserUpdateRequest(
        String name,
        String email,
        String passwordHash,
        String role,
        boolean active
) {}
