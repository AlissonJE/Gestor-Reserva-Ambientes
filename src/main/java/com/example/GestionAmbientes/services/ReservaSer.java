package com.example.GestionAmbientes.services;

import com.example.GestionAmbientes.dto.ReservaRequestDTO;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.entities.InstructorEnt;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.enums.EstadoReserva;
import com.example.GestionAmbientes.repositories.AmbienteRep;
import com.example.GestionAmbientes.repositories.InstructorRep;
import com.example.GestionAmbientes.repositories.ReservaRep;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaSer {
    private final ReservaRep reservaRep;
    private final AmbienteRep ambienteRep;
    private final InstructorRep instructorRep;

    public ReservaSer(ReservaRep reservaRep, AmbienteRep ambienteRep, InstructorRep instructorRep) {
        this.reservaRep = reservaRep;
        this.ambienteRep = ambienteRep;
        this.instructorRep = instructorRep;
    }

    public List<ReservaEnt> obtenerTodos() {
        return this.reservaRep.findAll();
    }

    public ReservaEnt crear(ReservaRequestDTO req) {
        AmbienteEnt ambiente = ambienteRep.findById(req.getAmbienteId())
                .orElseThrow(() -> new RuntimeException("Ambiente no encontrado"));
        InstructorEnt instructor = instructorRep.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));

        if (req.getInicio().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Regla 7: La fecha de inicio no puede estar en el pasado.");
        }

        long duracionHoras = ChronoUnit.HOURS.between(req.getInicio(), req.getFin());
        if (req.getInicio().getHour() < 6 || req.getFin().getHour() > 22 || (req.getFin().getHour() == 22 && req.getFin().getMinute() > 0)) {
            throw new RuntimeException("Regla 3: Las reservas deben estar entre las 6:00 y las 22:00.");
        }
        if (duracionHoras < 1 || duracionHoras > 4) {
            throw new RuntimeException("Regla 3: La duración debe ser entre 1 y 4 horas.");
        }

        if (!ambiente.getActivo()) {
            throw new RuntimeException("Regla 4: No se puede reservar un ambiente inactivo.");
        }

        if (req.getAprendices() > ambiente.getCapacidad()) {
            throw new RuntimeException("Regla 2: La cantidad de aprendices supera la capacidad del ambiente.");
        }

        LocalDateTime inicioDia = req.getInicio().toLocalDate().atStartOfDay();
        LocalDateTime finDia = inicioDia.plusDays(1);
        long reservasDia = reservaRep.countReservasInstructorDia(instructor.getId(), inicioDia, finDia);
        if (reservasDia >= 3) {
            throw new RuntimeException("Regla 5: El instructor ya tiene 3 reservas activas para este día.");
        }

        List<ReservaEnt> conflictos = reservaRep.findSolapamientosActivos(ambiente.getId(), instructor.getId(), req.getInicio(), req.getFin());
        if (!conflictos.isEmpty()) {
            throw new RuntimeException("Regla 1: El ambiente o el instructor ya tienen una reserva activa que se solapa en este horario.");
        }

        ReservaEnt nuevaReserva = new ReservaEnt();
        nuevaReserva.setAmbiente(ambiente);
        nuevaReserva.setInstructor(instructor);
        nuevaReserva.setAprendices(req.getAprendices());
        nuevaReserva.setInicio(req.getInicio());
        nuevaReserva.setFin(req.getFin());
        nuevaReserva.setEstado(EstadoReserva.ACTIVA);

        return reservaRep.save(nuevaReserva);
    }

    public ReservaEnt cancelar(Long id) {
        ReservaEnt reserva = reservaRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        long horasRestantes = ChronoUnit.HOURS.between(LocalDateTime.now(), reserva.getInicio());
        if (horasRestantes < 2) {
            throw new RuntimeException("Regla 6: Una reserva solo puede cancelarse con al menos 2 horas de anticipación.");
        }

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new RuntimeException("La reserva ya se encuentra cancelada.");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        return reservaRep.save(reserva);
    }
}