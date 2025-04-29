1. Obtener todos los jugadores de un equipo específico
@Query(value = "SELECT * FROM jugador WHERE id_equipo = :idEquipo", nativeQuery = true)
List<Jugador> findJugadoresByEquipoNative(@Param("idEquipo") Long idEquipo);

@Query(value = """
    SELECT j.* 
    FROM jugador j
    JOIN equipo e ON j.id_equipo = e.id_equipo
    WHERE e.nombre = :nombreEquipo
    """, nativeQuery = true)
List<Jugador> findJugadoresByNombreEquipo(@Param("nombreEquipo") String nombreEquipo);

2. Obtener los jugadores que han marcado más de X goles
@Query(value = """
    SELECT j.*, SUM(ej.goles) AS total_goles
    FROM jugador j
    JOIN estadisticas_jugador ej ON j.id_jugador = ej.id_jugador
    GROUP BY j.id_jugador
    HAVING SUM(ej.goles) > :minGoles
    ORDER BY total_goles DESC
    """, nativeQuery = true)
List<Object[]> findJugadoresConMasGolesQue(@Param("minGoles") int minGoles);

3. Obtener el número total de goles marcados por un equipo en todos sus partidos
@Query(value = """
    SELECT 
        e.nombre AS equipo,
        SUM(CASE 
            WHEN p.equipo_local = :idEquipo THEN p.goles_local
            WHEN p.equipo_visita = :idEquipo THEN p.goles_visita
            ELSE 0
        END) AS total_goles
    FROM partido p
    JOIN equipo e ON e.id_equipo = :idEquipo
    WHERE p.equipo_local = :idEquipo OR p.equipo_visita = :idEquipo
    GROUP BY e.nombre
    """, nativeQuery = true)
Map<String, Object> getTotalGolesByEquipo(@Param("idEquipo") Long idEquipo);

@Query(value = """
    SELECT 
        SUM(CASE 
            WHEN p.equipo_local = e.id_equipo THEN p.goles_local
            WHEN p.equipo_visita = e.id_equipo THEN p.goles_visita
            ELSE 0
        END) AS total_goles
    FROM partido p
    JOIN equipo e ON e.nombre = :nombreEquipo
    WHERE p.equipo_local = e.id_equipo OR p.equipo_visita = e.id_equipo
    """, nativeQuery = true)
Integer getTotalGolesByNombreEquipo(@Param("nombreEquipo") String nombreEquipo);

4. Obtener los resultados de todos los partidos indicando los nombres de los equipos
@Query(value = """
    SELECT 
        p.id_partido,
        p.fecha,
        p.estadio,
        el.nombre AS equipo_local,
        p.goles_local,
        ev.nombre AS equipo_visita,
        p.goles_visita,
        CASE
            WHEN p.goles_local > p.goles_visita THEN el.nombre
            WHEN p.goles_local < p.goles_visita THEN ev.nombre
            ELSE 'Empate'
        END AS ganador
    FROM partido p
    JOIN equipo el ON p.equipo_local = el.id_equipo
    JOIN equipo ev ON p.equipo_visita = ev.id_equipo
    ORDER BY p.fecha DESC
    """, nativeQuery = true)
List<Object[]> findAllPartidosConNombresEquipos();

@Query(value = """
    SELECT 
        p.id_partido,
        p.fecha,
        p.estadio,
        el.nombre AS equipo_local,
        p.goles_local,
        ev.nombre AS equipo_visita,
        p.goles_visita
    FROM partido p
    JOIN equipo el ON p.equipo_local = el.id_equipo
    JOIN equipo ev ON p.equipo_visita = ev.id_equipo
    WHERE p.fecha BETWEEN :fechaInicio AND :fechaFin
    ORDER BY p.fecha DESC
    """, nativeQuery = true)
List<Object[]> findPartidosConNombresEntreFechas(
    @Param("fechaInicio") LocalDate fechaInicio, 
    @Param("fechaFin") LocalDate fechaFin);

