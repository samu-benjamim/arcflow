package com.samu.dev.arcflow.dto.user;

public record UserCreateRequest(
        String name,
        String email,
        String passwordHash,
        String role
) {}
