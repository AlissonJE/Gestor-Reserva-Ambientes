package com.example.GestionAmbientes.repositories;

import com.example.GestionAmbientes.entities.ReservaEnt;
import com.example.GestionAmbientes.enums.EstadoReserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRep extends JpaRepository<ReservaEnt, Long> {

    @Query("SELECT r FROM ReservaEnt r WHERE r.ambiente.id = :ambienteId " +
           "AND r.estado = 'ACTIVA' AND r.inicio < :fin AND r.fin > :inicio")
    List<ReservaEnt> findSolapamientos(@Param("ambienteId") Long ambienteId,
                                       @Param("inicio") LocalDateTime inicio,
                                       @Param("fin") LocalDateTime fin);

    long countByInstructorAndEstadoAndInicioBetween(String instructor, EstadoReserva estado,
                                                    LocalDateTime desde, LocalDateTime hasta);

    List<ReservaEnt> findByAmbienteIdAndEstadoAndInicioBetween(Long ambienteId, EstadoReserva estado,
                                                               LocalDateTime desde, LocalDateTime hasta);

    @Query("SELECT r FROM ReservaEnt r WHERE r.ambiente.id = :ambienteId " +
           "AND r.estado = 'ACTIVA' AND r.inicio >= :desde AND r.inicio < :hasta")
    List<ReservaEnt> findByAmbienteAndFechaActivas(@Param("ambienteId") Long ambienteId,
                                                   @Param("desde") LocalDateTime desde,
                                                   @Param("hasta") LocalDateTime hasta);
}