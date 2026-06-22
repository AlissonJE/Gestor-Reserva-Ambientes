package com.example.GestionAmbientes.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
public class ReservaEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ambiente_id")
    private AmbienteEnt ambiente;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private InstructorEnt instructor;

    private LocalDateTime inicio;
    private LocalDateTime fin;
    private int aprendices;
    private String estado;
}