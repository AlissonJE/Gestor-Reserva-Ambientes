package com.example.GestionAmbientes.repositories;

import com.example.GestionAmbientes.entities.ReservaEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRep extends JpaRepository<ReservaEnt, Long> {

    @Query("SELECT r FROM ReservaEnt r WHERE (r.ambiente.id = :idAmbiente OR r.instructor.id = :idInstructor) " +
           "AND r.estado = 'ACTIVA' AND (r.inicio < :fin AND r.fin > :inicio)")
    List<ReservaEnt> findSolapamientosActivos(
            @Param("idAmbiente") Long idAmbiente,
            @Param("idInstructor") Long idInstructor,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT COUNT(r) FROM ReservaEnt r WHERE r.instructor.id = :idInstructor " +
           "AND r.estado = 'ACTIVA' AND r.inicio >= :inicioDia AND r.inicio < :finDia")
    long countReservasInstructorDia(
            @Param("idInstructor") Long idInstructor,
            @Param("inicioDia") LocalDateTime inicioDia,
            @Param("finDia") LocalDateTime finDia
    );
    
    @Query("SELECT r FROM ReservaEnt r WHERE r.ambiente.id = :idAmbiente " +
           "AND r.estado = 'ACTIVA' AND r.inicio >= :inicioDia AND r.inicio < :finDia")
    List<ReservaEnt> findByAmbienteAndFechaActivas(
            @Param("idAmbiente") Long idAmbiente,
            @Param("inicioDia") LocalDateTime inicioDia,
            @Param("finDia") LocalDateTime finDia
    );
}