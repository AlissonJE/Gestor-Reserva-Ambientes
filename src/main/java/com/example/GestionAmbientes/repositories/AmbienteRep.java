package com.example.GestionAmbientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.GestionAmbientes.entities.AmbienteEnt;

@Repository
public interface AmbienteRep extends JpaRepository<AmbienteEnt, Long> {
    boolean existsByNombre(String nombre);
}