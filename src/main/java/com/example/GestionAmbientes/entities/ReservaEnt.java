package com.example.GestionAmbientes.entities;

import com.example.GestionAmbientes.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data 
@NoArgsConstructor
public class ReservaEnt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id") 
    private InstructorEnt instructor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ambiente_id") 
    private AmbienteEnt ambiente; 

    private Integer aprendices; 
    
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
    
    private LocalDateTime inicio;
    private LocalDateTime fin;
}