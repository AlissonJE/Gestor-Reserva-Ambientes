package com.example.GestionAmbientes.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.GestionAmbientes.entities.InstructorEnt;
import com.example.GestionAmbientes.repositories.InstructorRep;

@Service
public class InstructorSer {
    private final InstructorRep repository;

    public InstructorSer(InstructorRep repository) {
        this.repository = repository;
    }

    public List<InstructorEnt> obtenerTodos() {
        return this.repository.findAll();
    }

    public InstructorEnt crear(InstructorEnt instructor) {
        if (repository.findByEmail(instructor.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un instructor con ese email.");
        }
        return this.repository.save(instructor);
    }
}