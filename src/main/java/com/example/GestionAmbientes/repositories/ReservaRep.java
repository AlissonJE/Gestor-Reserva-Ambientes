package com.example.GestionAmbientes.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.GestionAmbientes.entities.ReservaEnt;

public interface ReservaRep extends JpaRepository<ReservaEnt, Long> {

        @Query("SELECT r FROM ReservaEnt r WHERE r.ambiente.id = :id AND (r.inicio < :fin AND r.fin > :inicio)")
        List<ReservaEnt> findSolapamientos(@Param("id") Long id,
                        @Param("inicio") LocalDateTime inicio,
                        @Param("fin") LocalDateTime fin);

        @Query("SELECT r.ambiente.nombre, COUNT(r) FROM ReservaEnt r GROUP BY r.ambiente.nombre ORDER BY COUNT(r) DESC")
        List<Object[]> contarReservasPorAmbiente();
}