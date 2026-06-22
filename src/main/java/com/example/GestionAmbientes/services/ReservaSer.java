package com.example.GestionAmbientes.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.repositories.ReservaRep;

@Service
public class ReservaSer {

    private final ReservaRep repository;

    public ReservaSer(ReservaRep repository) {
        this.repository = repository;
    }

    public List<ReservaEnt> obtenerTodos() {
        return this.repository.findAll();
    }

    public ReservaEnt crear(ReservaEnt reserva) {
        return this.repository.save(reserva);
    }
}