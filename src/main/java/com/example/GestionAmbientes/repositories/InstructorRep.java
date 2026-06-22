package com.example.GestionAmbientes.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionAmbientes.entities.InstructorEnt;

public interface InstructorRep extends JpaRepository<InstructorEnt, Long> {
    Optional<InstructorEnt> findByEmail(String email);
}