package com.example.GestionAmbientes.services;

import com.example.GestionAmbientes.dto.OcupacionDTO;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.enums.EstadoReserva;
import com.example.GestionAmbientes.repositories.AmbienteRep;
import com.example.GestionAmbientes.repositories.ReservaRep;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteSer {

    private static final double HORAS_INSTITUCIONALES = 16.0; 

    private final AmbienteRep ambienteRepository;
    private final ReservaRep reservaRepository;

    public ReporteSer(AmbienteRep ambienteRepository, ReservaRep reservaRepository) {
        this.ambienteRepository = ambienteRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<OcupacionDTO> ocupacionPorFecha(LocalDate fecha) {
        LocalDateTime desde = fecha.atStartOfDay();
        LocalDateTime hasta = fecha.plusDays(1).atStartOfDay();

        List<AmbienteEnt> ambientes = ambienteRepository.findAll();

        return ambientes.stream().map(ambiente -> {
            
            List<ReservaEnt> reservas = reservaRepository.findByAmbienteAndFechaActivas(
                    ambiente.getId(), desde, hasta);

            double minutos = reservas.stream()
                    .mapToLong(r -> Duration.between(r.getInicio(), r.getFin()).toMinutes())
                    .sum();
                    
            double horas = minutos / 60.0;
            double porcentaje = (horas / HORAS_INSTITUCIONALES) * 100.0;

            return new OcupacionDTO(ambiente.getId(), ambiente.getNombre(), horas, porcentaje);
        }).toList();
    }
}