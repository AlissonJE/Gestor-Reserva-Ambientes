package com.example.GestionAmbientes.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        List<ReservaEnt> conflictos = repository.findSolapamientos(
                reserva.getAmbiente().getId(),
                reserva.getInicio(),
                reserva.getFin());

        if (!conflictos.isEmpty()) {
            throw new RuntimeException("El ambiente ya tiene una reserva en este horario.");
        }
        return this.repository.save(reserva);
    }

    public Map<String, Long> obtenerReporteOcupacion() {
        List<Object[]> resultados = repository.contarReservasPorAmbiente();
        Map<String, Long> reporte = new LinkedHashMap<>();
        for (Object[] obj : resultados) {
            reporte.put((String) obj[0], (Long) obj[1]);
        }
        return reporte;
    }
}