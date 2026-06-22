package com.example.GestionAmbientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionAmbientes.entities.AmbienteEnt;

public interface AmbienteRep extends JpaRepository<AmbienteEnt, Long> {
    boolean existsByNombre(String nombre);
}