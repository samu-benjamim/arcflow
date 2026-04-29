package com.samu.dev.arcflow.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Nome do escritório obrigatório")
        String officeName,

        @NotBlank(message = "CNPJ é obrigatório")
        @Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos (sem formatação)")
        String cnpj,

        @NotBlank(message = "Email do escritório é obrigatório")
        @Email(message = "Email do escritório inválido")
        String officeEmail,

        @NotBlank(message = "Nome do responsável é obrigatório")
        String name,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        String password) {}
