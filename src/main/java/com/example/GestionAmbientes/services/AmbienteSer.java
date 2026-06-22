package com.example.GestionAmbientes.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.repositories.AmbienteRep;

@Service
public class AmbienteSer {
    private final AmbienteRep repository;

    public AmbienteSer(AmbienteRep repository) {
        this.repository = repository;
    }

    public List<AmbienteEnt> obtenerTodos() {
        return this.repository.findAll();
    }

    public AmbienteEnt crear(AmbienteEnt ambiente) {
        if (repository.existsByNombre(ambiente.getNombre())) {
            throw new RuntimeException("Ya existe un ambiente con ese nombre.");
        }
        return this.repository.save(ambiente);
    }

    public AmbienteEnt buscarPorId(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ambiente no encontrado"));
    }
}