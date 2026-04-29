package com.samu.dev.arcflow.dto.timeentry;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TimeEntryCreateRequest(

        @NotNull(message = "Horas trabalhadas são obrigatórias")
        @DecimalMin(value = "0.25", message = "Mínimo de 0.25 horas por lançamento")
        @DecimalMax(value = "24.00", message = "Máximo de 24 horas por lançamento")
        BigDecimal hoursWorked,

        @NotNull(message = "Data é obrigatória")
        @PastOrPresent(message = "Data não pode ser futura")
        LocalDate date,

        @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
        String description
) {}
