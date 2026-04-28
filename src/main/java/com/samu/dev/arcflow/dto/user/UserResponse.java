package com.samu.dev.arcflow.dto.user;

import com.samu.dev.arcflow.model.types.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role,
        boolean active
) {}
