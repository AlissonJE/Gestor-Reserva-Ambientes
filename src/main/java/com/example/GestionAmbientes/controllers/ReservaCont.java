package com.example.GestionAmbientes.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.services.ReservaSer;

@RestController
@RequestMapping("/api/reservas")
public class ReservaCont {

    private final ReservaSer service;

    public ReservaCont(ReservaSer service) {
        this.service = service;
    }

    @GetMapping
    public List<ReservaEnt> getAll() {
        return this.service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<ReservaEnt> create(@RequestBody ReservaEnt reserva) {
        return ResponseEntity.status(201).body(this.service.crear(reserva));
    }
}