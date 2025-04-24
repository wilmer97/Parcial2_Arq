package com.example.Parcial2Arq.Repository;

import com.example.Parcial2Arq.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :idEquipo", nativeQuery = true)
    List<Jugador> findJugadoresByEquipoNative(@Param("idEquipo") Long idEquipo);

    // Consulta nativa para obtener los jugadores que han marcado mas de x goles
    @Query(value = """
        SELECT j.* 
        FROM jugador j
        JOIN estadisticas_jugador ej ON j.id_jugador = ej.id_jugador
        GROUP BY j.id_jugador
        HAVING SUM(ej.goles) > :minGoles
        ORDER BY SUM(ej.goles) DESC
        """, nativeQuery = true)

    List<Jugador> findJugadoresConMasGolesQue(@Param("minGoles") int minGoles);

    // Consulta derivada: Buscar jugadores por posicion
    List<Jugador> findByPosicion(String posicion);

    // Consulta derivada: Buscar jugadores por nacionalidad
    List<Jugador> findByNacionalidad(String nacionalidad);

    // Consulta JPQL: Buscar Jugadores por equipo y posicion
    @Query("SELECT j FROM Jugador j WHERE j.equipo.idEquipo = :idEquipo AND j.posicion = :posicion")
    List<Jugador> findByEquipoAndPosicion(@Param("idEquipo") Long idEquipo, @Param("posicion") String posicion);
}
