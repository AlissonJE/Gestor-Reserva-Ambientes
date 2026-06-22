package com.example.GestionAmbientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcupacionDTO {
    private Long ambienteId;
    private String nombreAmbiente;
    private double horasReservadas;
    private double porcentajeOcupacion;
}