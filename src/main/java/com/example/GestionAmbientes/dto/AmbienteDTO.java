package com.example.GestionAmbientes.dto;

import com.example.GestionAmbientes.enums.TipoAmbiente;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmbienteDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El tipo es obligatorio (SALA, LABORATORIO o AUDITORIO)")
    private TipoAmbiente tipo;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidad;

    private Boolean activo = true;
}