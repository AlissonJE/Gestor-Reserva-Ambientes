package com.example.GestionAmbientes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaRequestDTO {
    @NotNull
    private Long instructorId;

    @NotNull
    private Long ambienteId;

    @NotNull
    @Min(1)
    private Integer aprendices;

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fin;
}