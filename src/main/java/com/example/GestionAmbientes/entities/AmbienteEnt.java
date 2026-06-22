package com.example.GestionAmbientes.entities;

import com.example.GestionAmbientes.enums.TipoAmbiente;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private TipoAmbiente tipo;

    private Integer capacidad;

    private Boolean activo = true;
}