package com.example.GestionAmbientes.services;

import com.example.GestionAmbientes.dto.AmbienteDTO;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.exceptions.BusinessConflictException;
import com.example.GestionAmbientes.exceptions.ResourceNotFoundException;
import com.example.GestionAmbientes.repositories.AmbienteRep;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmbienteSer {

    private final AmbienteRep repository;

    public AmbienteSer(AmbienteRep repository) {
        this.repository = repository;
    }

    public List<AmbienteEnt> obtenerTodos() {
        return repository.findAll();
    }

    public AmbienteEnt crear(AmbienteDTO dto) {
        if (repository.existsByNombre(dto.getNombre())) {
            throw new BusinessConflictException("Nombre único", "Ya existe un ambiente con ese nombre.");
        }

        AmbienteEnt ambiente = new AmbienteEnt();
        ambiente.setNombre(dto.getNombre());
        ambiente.setTipo(dto.getTipo());
        ambiente.setCapacidad(dto.getCapacidad());
        ambiente.setActivo(dto.getActivo() == null ? true : dto.getActivo());

        return repository.save(ambiente);
    }

    public AmbienteEnt buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente no encontrado."));
    }

    public List<AmbienteEnt> obtenerDisponibles(LocalDateTime inicio, LocalDateTime fin) {
        return repository.findDisponibles(inicio, fin);
    }
}