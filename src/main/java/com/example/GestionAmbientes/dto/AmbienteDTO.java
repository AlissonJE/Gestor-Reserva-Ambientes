package com.example.GestionAmbientes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmbienteDTO {

    @NotNull
    @Min(1)
    private int capacidad;

    @NotBlank
    private String tipo = "";

    @NotBlank
    private String nombre = "";

    private int id;

    private boolean activo = true;

}
