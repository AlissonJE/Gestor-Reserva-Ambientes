package com.example.GestionAmbientes.controllers;

import com.example.GestionAmbientes.dto.OcupacionDTO;
import com.example.GestionAmbientes.services.ReporteSer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteCont {

    private final ReporteSer service;

    public ReporteCont(ReporteSer service) {
        this.service = service;
    }

    @GetMapping("/ocupacion")
    public List<OcupacionDTO> ocupacion(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return service.ocupacionPorFecha(fecha);
    }
}