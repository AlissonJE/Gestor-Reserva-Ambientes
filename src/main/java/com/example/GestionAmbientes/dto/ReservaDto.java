package com.example.GestionAmbientes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaDto {

    @NotNull(message = "El id del ambiente es obligatorio")
    private Long ambienteId;

    @NotBlank(message = "El nombre del instructor es obligatorio")
    private String instructor;

    @NotNull(message = "La fecha y hora de inicio es obligatoria")
    private LocalDateTime inicio;

    @NotNull(message = "La fecha y hora de fin es obligatoria")
    private LocalDateTime fin;

    @NotNull(message = "El número de aprendices es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 aprendiz")
    private Integer aprendices;
}