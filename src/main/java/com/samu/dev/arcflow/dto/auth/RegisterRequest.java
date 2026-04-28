package com.samu.dev.arcflow.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Nome do escritório obrigatório")
        String officeName,

        @Size(min = 14, max = 14, message = "CNPJ deve ter 14 caracteres")
        String cnpj,

        String officeEmail,

        @NotBlank(message = "Nome obrigatório")
        String name,

        @Email(message = "Email inválido")
        @NotBlank
        String email,

        @Size(min = 6, message = "Senha mínimo 6 caracteres")
        String password) {}
