package com.example.Parcial2Arq.Service;

import com.example.Parcial2Arq.Model.EstadisticasJugador;
import com.example.Parcial2Arq.Model.Jugador;
import com.example.Parcial2Arq.Model.Partido;
import com.example.Parcial2Arq.Repository.EstadisticasJugadorRepository;
import com.example.Parcial2Arq.Repository.JugadorRepository;
import com.example.Parcial2Arq.Repository.PartidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EstadisticasJugadorService {

    private final EstadisticasJugadorRepository estadisticasRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    public EstadisticasJugadorService(EstadisticasJugadorRepository estadisticasRepository,
                                      JugadorRepository jugadorRepository,
                                      PartidoRepository partidoRepository) {
        this.estadisticasRepository = estadisticasRepository;
        this.jugadorRepository = jugadorRepository;
        this.partidoRepository = partidoRepository;
    }

    public EstadisticasJugador crearEstadistica(EstadisticasJugador estadistica, Long idJugador, Long idPartido) {
        Jugador jugador = jugadorRepository.findById(idJugador)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + idJugador));

        Partido partido = partidoRepository.findById(idPartido)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + idPartido));

        estadistica.setJugador(jugador);
        estadistica.setPartido(partido);

        return estadisticasRepository.save(estadistica);
    }

    public EstadisticasJugador actualizarEstadistica(Long id, EstadisticasJugador estadisticaActualizada) {
        EstadisticasJugador estadistica = estadisticasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con id: " + id));

        estadistica.setMinutosJugados(estadisticaActualizada.getMinutosJugados());
        estadistica.setGoles(estadisticaActualizada.getGoles());
        estadistica.setAsistencias(estadisticaActualizada.getAsistencias());
        estadistica.setTarjetas_amarillas(estadisticaActualizada.getTarjetas_amarillas());
        estadistica.setTarjetas_Rojas(estadisticaActualizada.getTarjetas_Rojas());

        return estadisticasRepository.save(estadistica);
    }

    @Transactional(readOnly = true)
    public List<EstadisticasJugador> obtenerTodasEstadisticas() {
        return estadisticasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EstadisticasJugador obtenerEstadisticaPorId(Long id) {
        return estadisticasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con id: " + id));
    }

    public void eliminarEstadistica(Long id) {
        EstadisticasJugador estadistica = estadisticasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con id: " + id));

        estadisticasRepository.delete(estadistica);
    }

    @Transactional(readOnly = true)
    public List<EstadisticasJugador> obtenerEstadisticasPorJugador(Long idJugador) {
        return estadisticasRepository.findByJugadorIdJugador(idJugador);
    }

    @Transactional(readOnly = true)
    public List<EstadisticasJugador> obtenerEstadisticasPorPartido(Long idPartido) {
        return estadisticasRepository.findByPartidoIdPartido(idPartido);
    }

    @Transactional(readOnly = true)
    public Integer obtenerTotalGolesJugador(Long idJugador) {
        return estadisticasRepository.getTotalGolesByJugador(idJugador);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> obtenerEstadisticasCompletasJugador(Long idJugador) {
        Object[] resultado = estadisticasRepository.getEstadisticasCompletasJugador(idJugador).get(0);

        return Map.of(
                "nombre", resultado[0],
                "posicion", resultado[1],
                "equipo", resultado[2],
                "partidosJugados", resultado[3],
                "minutosTotales", resultado[4],
                "golesTotales", resultado[5],
                "asistenciasTotales", resultado[6],
                "tarjetasAmarillas", resultado[7],
                "tarjetasRojas", resultado[8]
        );
    }
}
