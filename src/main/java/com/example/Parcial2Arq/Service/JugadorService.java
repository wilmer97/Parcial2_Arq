package com.example.Parcial2Arq.Service;

import com.example.Parcial2Arq.Model.Equipo;
import com.example.Parcial2Arq.Model.Jugador;
import com.example.Parcial2Arq.Repository.EquipoRepository;
import com.example.Parcial2Arq.Repository.JugadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;

    public JugadorService(JugadorRepository jugadorRepository, EquipoRepository equipoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
    }

    public Jugador crearJugador(Jugador jugador, Long idEquipo) {
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + idEquipo));

        jugador.setEquipo(equipo);
        return jugadorRepository.save(jugador);
    }

    public Jugador actualizarJugador(Long id, Jugador jugadorActualizado) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));

        jugador.setNombre(jugadorActualizado.getNombre());
        jugador.setPosicion(jugadorActualizado.getPosicion());
        jugador.setDorsal(jugadorActualizado.getDorsal());
        jugador.setFechaNac(jugadorActualizado.getFechaNac());
        jugador.setNacionalidad(jugadorActualizado.getNacionalidad());

        return jugadorRepository.save(jugador);
    }

    @Transactional(readOnly = true)
    public List<Jugador> obtenerTodosJugadores() {
        return jugadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Jugador obtenerJugadorPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
    }

    public void eliminarJugador(Long id) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));

        jugadorRepository.delete(jugador);
    }

    @Transactional(readOnly = true)
    public List<Jugador> obtenerJugadoresPorEquipo(Long idEquipo) {
        return jugadorRepository.findJugadoresByEquipoNative(idEquipo);
    }

    @Transactional(readOnly = true)
    public List<Jugador> obtenerJugadoresConMasGolesQue(int minGoles) {
        return jugadorRepository.findJugadoresConMasGolesQue(minGoles);
    }

    @Transactional(readOnly = true)
    public List<Jugador> buscarJugadoresPorPosicion(String posicion) {
        return jugadorRepository.findByPosicion(posicion);
    }
}
