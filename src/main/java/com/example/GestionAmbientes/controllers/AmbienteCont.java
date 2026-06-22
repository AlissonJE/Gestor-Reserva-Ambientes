package com.example.GestionAmbientes.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.services.AmbienteSer;

@RestController
@RequestMapping("/api/ambientes")
public class AmbienteCont {

    private final AmbienteSer service;

    public AmbienteCont(AmbienteSer service) {
        this.service = service;
    }

    @GetMapping("/get")
    public List<AmbienteEnt> getAll() {
        return this.service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<AmbienteEnt> create(@RequestBody AmbienteEnt ambiente) {
        AmbienteEnt nuevoAmbiente = this.service.crear(ambiente);
        return ResponseEntity.status(201).body(nuevoAmbiente);
    }
}