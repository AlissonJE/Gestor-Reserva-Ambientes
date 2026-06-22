package com.example.GestionAmbientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionAmbientes.entities.ReservaEnt;

public interface ReservaRep extends JpaRepository<ReservaEnt, Long> {
}