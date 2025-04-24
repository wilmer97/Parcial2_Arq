package com.example.Parcial2Arq.Repository;

import com.example.Parcial2Arq.Model.EstadisticasJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstadisticasJugadorRepository extends JpaRepository<EstadisticasJugador, Long> {

    // Consulta derivada: Buscar estadísticas por jugador
    List<EstadisticasJugador> findByJugadorIdJugador(Long idJugador);

    // Consulta derivada: Buscar estadísticas por partido
    List<EstadisticasJugador> findByPartidoIdPartido(Long idPartido);

    // Consulta JPQL: Obtener total de goles por jugador
    @Query("SELECT SUM(e.goles) FROM EstadisticasJugador e WHERE e.jugador.idJugador = :idJugador")
    Integer getTotalGolesByJugador(@Param("idJugador") Long idJugador);

    // Consulta nativa: Obtener estadísticas completas de un jugador
    @Query(value = """
        SELECT j.nombre, j.posicion, e.nombre AS equipo,
               COUNT(DISTINCT ej.id_partido) AS partidos_jugados,
               SUM(ej.minutos_jugados) AS minutos_totales,
               SUM(ej.goles) AS goles_totales,
               SUM(ej.asistencias) AS asistencias_totales,
               SUM(ej.tarjetas_amarillas) AS amarillas_totales,
               SUM(ej.tarjetas_rojas) AS rojas_totales
        FROM jugador j
        JOIN equipo e ON j.id_equipo = e.id_equipo
        JOIN estadisticas_jugador ej ON j.id_jugador = ej.id_jugador
        WHERE j.id_jugador = :idJugador
        GROUP BY j.nombre, j.posicion, e.nombre
        """, nativeQuery = true)
    List<Object[]> getEstadisticasCompletasJugador(@Param("idJugador") Long idJugador);
}
