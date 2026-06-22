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
        return this.repository.save(ambiente);
    }
}
