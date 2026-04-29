package com.samu.dev.arcflow.dto.task;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TaskCreateRequest(

        @NotBlank(message = "Título da task é obrigatório")
        @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
        String title,

        @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
        String description,

        @NotNull(message = "Fase é obrigatória")
        Long phaseId,

        Long userId,

        Integer order,

        @Positive(message = "Horas estimadas devem ser positivas")
        BigDecimal estimatedHours,

        @FutureOrPresent(message = "Prazo não pode ser no passado")
        LocalDate deadline
) {}
