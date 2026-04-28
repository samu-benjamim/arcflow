package com.samu.dev.arcflow.dto.user;

import com.samu.dev.arcflow.model.types.Role;

public record UserCreateRequest(
        String name,
        String email,
        String password,
        Role role
) {}
