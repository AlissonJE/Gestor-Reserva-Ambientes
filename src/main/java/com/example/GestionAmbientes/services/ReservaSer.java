package com.example.GestionAmbientes.services;

import com.example.GestionAmbientes.dto.ReservaDto;
import com.example.GestionAmbientes.entities.AmbienteEnt;
import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.enums.EstadoReserva;
import com.example.GestionAmbientes.exceptions.BusinessConflictException;
import com.example.GestionAmbientes.exceptions.ResourceNotFoundException;
import com.example.GestionAmbientes.exceptions.ValidationException;
import com.example.GestionAmbientes.repositories.ReservaRep;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaSer {

    private static final LocalTime HORA_APERTURA = LocalTime.of(6, 0);
    private static final LocalTime HORA_CIERRE = LocalTime.of(22, 0);
    private static final int DURACION_MIN_HORAS = 1;
    private static final int DURACION_MAX_HORAS = 4;
    private static final int LIMITE_RESERVAS_INSTRUCTOR_DIA = 3;
    private static final int HORAS_ANTICIPACION_CANCELACION = 2;

    private final ReservaRep repository;
    private final AmbienteSer ambienteSer;

    public ReservaSer(ReservaRep repository, AmbienteSer ambienteSer) {
        this.repository = repository;
        this.ambienteSer = ambienteSer;
    }

    public List<ReservaEnt> obtenerTodos() {
        return repository.findAll();
    }

    public ReservaEnt crear(ReservaDto dto) {
        AmbienteEnt ambiente = ambienteSer.buscarPorId(dto.getAmbienteId());

        validarRangoHorario(dto.getInicio(), dto.getFin());       
        validarNoEnElPasado(dto.getInicio());                  
        validarAmbienteActivo(ambiente);                         
        validarCapacidad(dto.getAprendices(), ambiente);        
        validarSolapamiento(ambiente.getId(), dto.getInicio(), dto.getFin()); 
        validarLimiteInstructor(dto.getInstructor(), dto.getInicio());        

        ReservaEnt reserva = new ReservaEnt();
        reserva.setAmbiente(ambiente);
        reserva.setInstructor(dto.getInstructor());
        reserva.setInicio(dto.getInicio());
        reserva.setFin(dto.getFin());
        reserva.setAprendices(dto.getAprendices());
        reserva.setEstado(EstadoReserva.ACTIVA);

        return repository.save(reserva);
    }

    public ReservaEnt cancelar(Long id) {                        
        ReservaEnt reserva = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada."));

        if (reserva.getEstado() != EstadoReserva.ACTIVA) {
            throw new BusinessConflictException("Cancelación con anticipación",
                    "Solo se pueden cancelar reservas en estado ACTIVA.");
        }

        if (LocalDateTime.now().plusHours(HORAS_ANTICIPACION_CANCELACION).isAfter(reserva.getInicio())) {
            throw new BusinessConflictException("Cancelación con anticipación",
                    "Solo se puede cancelar una reserva si faltan al menos " +
                    HORAS_ANTICIPACION_CANCELACION + " horas para su inicio.");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        return repository.save(reserva);
    }

    public List<ReservaEnt> obtenerActivasPorAmbienteYFecha(Long ambienteId, LocalDate fecha) {
        LocalDateTime desde = fecha.atStartOfDay();
        LocalDateTime hasta = fecha.plusDays(1).atStartOfDay();
        return repository.findByAmbienteIdAndEstadoAndInicioBetween(ambienteId, EstadoReserva.ACTIVA, desde, hasta);
    }

    // ---------- Validaciones de negocio ----------

    private void validarRangoHorario(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null || !fin.isAfter(inicio)) {
            throw new ValidationException("Horario institucional",
                    "La fecha de fin debe ser posterior a la fecha de inicio.");
        }
        if (inicio.toLocalTime().isBefore(HORA_APERTURA) || fin.toLocalTime().isAfter(HORA_CIERRE)) {
            throw new ValidationException("Horario institucional",
                    "Las reservas solo pueden estar entre las " + HORA_APERTURA + " y las " + HORA_CIERRE + ".");
        }
        long minutos = Duration.between(inicio, fin).toMinutes();
        if (minutos < DURACION_MIN_HORAS * 60L || minutos > DURACION_MAX_HORAS * 60L) {
            throw new ValidationException("Horario institucional",
                    "La reserva debe durar entre " + DURACION_MIN_HORAS + " y " + DURACION_MAX_HORAS + " horas.");
        }
    }

    private void validarNoEnElPasado(LocalDateTime inicio) {
        if (inicio.isBefore(LocalDateTime.now())) {
            throw new ValidationException("No se reserva en el pasado",
                    "La fecha de inicio debe ser posterior al momento actual.");
        }
    }

    private void validarAmbienteActivo(AmbienteEnt ambiente) {
        if (!Boolean.TRUE.equals(ambiente.getActivo())) {
            throw new BusinessConflictException("Ambientes inactivos",
                    "No se puede reservar un ambiente inactivo.");
        }
    }

    private void validarCapacidad(Integer aprendices, AmbienteEnt ambiente) {
        if (aprendices > ambiente.getCapacidad()) {
            throw new ValidationException("Capacidad",
                    "El número de aprendices (" + aprendices + ") supera la capacidad del ambiente (" +
                    ambiente.getCapacidad() + ").");
        }
    }

    private void validarSolapamiento(Long ambienteId, LocalDateTime inicio, LocalDateTime fin) {
        List<ReservaEnt> conflictos = repository.findSolapamientos(ambienteId, inicio, fin);
        if (!conflictos.isEmpty()) {
            throw new BusinessConflictException("Sin cruces de horario",
                    "El ambiente ya tiene una reserva ACTIVA que se cruza con ese horario.");
        }
    }

    private void validarLimiteInstructor(String instructor, LocalDateTime inicio) {
        LocalDate dia = inicio.toLocalDate();
        LocalDateTime desde = dia.atStartOfDay();
        LocalDateTime hasta = dia.plusDays(1).atStartOfDay();

        long reservasDelDia = repository.countByInstructorAndEstadoAndInicioBetween(
                instructor, EstadoReserva.ACTIVA, desde, hasta);

        if (reservasDelDia >= LIMITE_RESERVAS_INSTRUCTOR_DIA) {
            throw new BusinessConflictException("Límite por instructor",
                    "El instructor ya tiene " + LIMITE_RESERVAS_INSTRUCTOR_DIA + " reservas ACTIVAS ese día.");
        }
    }
}