package com.example.GestionAmbientes.controllers;

import com.example.GestionAmbientes.dto.ReservaDto;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.services.ReservaSer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaCont {

    private final ReservaSer service;

    public ReservaCont(ReservaSer service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReservaEnt> create(@Valid @RequestBody ReservaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public List<ReservaEnt> getAll() {
        return service.obtenerTodos();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaEnt> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }
}