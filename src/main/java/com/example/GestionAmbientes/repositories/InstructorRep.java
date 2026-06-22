package com.example.GestionAmbientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionAmbientes.entities.InstructorEnt;

public interface InstructorRep extends JpaRepository<InstructorEnt, Long> {
}