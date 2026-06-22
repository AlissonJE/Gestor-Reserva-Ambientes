package com.example.GestionAmbientes.repositories;

import com.example.GestionAmbientes.entities.AmbienteEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface AmbienteRep extends JpaRepository<AmbienteEnt, Long> {

    boolean existsByNombre(String nombre);

       @Query("SELECT a FROM AmbienteEnt a WHERE a.activo = true AND a.id NOT IN " +
           "(SELECT r.ambiente.id FROM ReservaEnt r WHERE r.estado = 'ACTIVA' " +
           "AND r.inicio < :fin AND r.fin > :inicio)")
    List<AmbienteEnt> findDisponibles(@Param("inicio") LocalDateTime inicio,
                                       @Param("fin") LocalDateTime fin);
}