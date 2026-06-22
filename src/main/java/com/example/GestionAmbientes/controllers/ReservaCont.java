package com.example.GestionAmbientes.controllers;

import com.example.GestionAmbientes.dto.ReservaRequestDTO;
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

    @GetMapping("/get")
    public List<ReservaEnt> getAll() {
        return this.service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReservaRequestDTO req) {
        try {
            ReservaEnt nuevaReserva = this.service.crear(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            ReservaEnt reservaCancelada = this.service.cancelar(id);
            return ResponseEntity.status(HttpStatus.OK).body(reservaCancelada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}