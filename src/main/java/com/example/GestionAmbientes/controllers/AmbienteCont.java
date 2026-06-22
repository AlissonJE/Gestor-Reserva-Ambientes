package com.example.GestionAmbientes.controllers;

import com.example.GestionAmbientes.dto.AmbienteDTO;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.services.AmbienteSer;
import com.example.GestionAmbientes.services.ReservaSer;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ambientes")
public class AmbienteCont {

    private final AmbienteSer service;
    private final ReservaSer reservaService;

    public AmbienteCont(AmbienteSer service, ReservaSer reservaService) {
        this.service = service;
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<AmbienteEnt> create(@Valid @RequestBody AmbienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public List<AmbienteEnt> getAll() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}/reservas")
    public List<ReservaEnt> reservasDelAmbiente(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return reservaService.obtenerActivasPorAmbienteYFecha(id, fecha);
    }

    @GetMapping("/disponibles")
    public List<AmbienteEnt> disponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return service.obtenerDisponibles(inicio, fin);
    }
}