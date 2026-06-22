package com.example.GestionAmbientes.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaDto {

    @NotNull(message = "El ID del ambiente es obligatorio")
    private Long ambienteId;

    @NotBlank(message = "El instructor es obligatorio")
    private String instructor;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio debe ser actual o futura")
    private LocalDateTime inicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDateTime fin;

    @Min(value = 1, message = "Debe haber al menos 1 aprendiz")
    private int aprendices;

    private String estado = "PROGRAMADA";
}