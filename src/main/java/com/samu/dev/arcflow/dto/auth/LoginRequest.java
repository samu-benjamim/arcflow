package com.samu.dev.arcflow.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Email inválido")
        @NotBlank
        String email,

        @NotBlank(message = "Senha obrigatória")
        String password
) {}
