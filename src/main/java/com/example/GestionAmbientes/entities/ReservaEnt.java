package com.example.GestionAmbientes.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.example.GestionAmbientes.enums.EstadoReserva;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
public class ReservaEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ambiente_id")
    private AmbienteEnt ambiente;

    private String instructor;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    private Integer aprendices;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
}