package com.samu.dev.arcflow.dto.user;

import com.samu.dev.arcflow.model.types.Role;

public record UserUpdateRequest(
        String name,
        String email,
        String passwordHash,
        Role role,
        boolean active
) {}
