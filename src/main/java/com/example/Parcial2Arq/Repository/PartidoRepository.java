package com.example.Parcial2Arq.Repository;

import com.example.Parcial2Arq.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    // Consulta nativa: Obtener los resultados de todos los partidos con nombres de equipos
    @Query(value = """
        SELECT p.id_partido, p.fecha, p.estadio, 
               el.nombre AS equipo_local, p.goles_local,
               ev.nombre AS equipo_visita, p.goles_visita
        FROM partido p
        JOIN equipo el ON p.equipo_local = el.id_equipo
        JOIN equipo ev ON p.equipo_visita = ev.id_equipo
        ORDER BY p.fecha DESC
        """, nativeQuery = true)
    List<Objects[]> findAllPartidosConNombresEquipos();

    // Consulta nativa: Obtener el numero total de los goles marcados por un equipo
    @Query(value = """
        SELECT SUM(CASE 
            WHEN p.equipo_local = :idEquipo THEN p.goles_local
            WHEN p.equipo_visita = :idEquipo THEN p.goles_visita
            ELSE 0
        END) AS total_goles
        FROM partido p
        WHERE p.equipo_local = :idEquipo OR p.equipo_visita = :idEquipo
        """, nativeQuery = true)
    Integer getTotalGolesByEquipo(@Param("idEquipo") Long idEquipo);

    // Consulta derivada: Buscar partidos por fecha
    List<Partido> findByFecha(LocalDate fecha);

    // Consulta derivada: Buscar partidos por fechas
    List<Partido> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // Consulta JPQL: Buscar los partidos donde un equipo participo
    @Query("SELECT p FROM Partido p WHERE p.equipoLocal.idEquipo = :idEquipo OR p.equipoVisita.idEquipo = :idEquipo")
    List<Partido> findPartidosByEquipo(@Param("idEquipo") Long idEquipo);

}
