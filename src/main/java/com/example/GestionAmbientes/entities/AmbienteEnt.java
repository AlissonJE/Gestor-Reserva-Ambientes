package com.example.GestionAmbientes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ambientes")
@Data
@NoArgsConstructor
public class AmbienteEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @NotBlank
    private String tipo;

    @NotNull
    @Min(1)
    private Integer capacidad;

    private Boolean activo = true;
}