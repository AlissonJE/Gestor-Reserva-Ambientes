package com.example.GestionAmbientes.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class ReservaEnt {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private AmbienteEnt ambiente;

    private String instructor;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private int aprendices;
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AmbienteEnt getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(AmbienteEnt ambiente) {
        this.ambiente = ambiente;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public int getAprendices() {
        return aprendices;
    }

    public void setAprendices(int aprendices) {
        this.aprendices = aprendices;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}