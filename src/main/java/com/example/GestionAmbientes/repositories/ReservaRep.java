package com.example.GestionAmbientes.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionAmbientes.entities.ReservaEnt;

public interface ReservaRep extends JpaRepository<ReservaEnt, Long> {

    List<ReservaEnt> findSolapamientos(Long id, LocalDateTime inicio, LocalDateTime fin);
}