package com.example.GestionAmbientes.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.GestionAmbientes.entities.InstructorEnt;
import com.example.GestionAmbientes.services.InstructorSer;

@RestController
@RequestMapping("/api/instructores")
public class InstructorCont {

    private final InstructorSer service;

    public InstructorCont(InstructorSer service) {
        this.service = service;
    }

    @GetMapping
    public List<InstructorEnt> getAll() {
        return this.service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<InstructorEnt> create(@RequestBody InstructorEnt instructor) {
        return ResponseEntity.status(201).body(this.service.crear(instructor));
    }
}