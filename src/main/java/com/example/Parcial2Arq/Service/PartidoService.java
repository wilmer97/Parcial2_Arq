package com.example.Parcial2Arq.Service;

import com.example.Parcial2Arq.Model.Equipo;
import com.example.Parcial2Arq.Model.Partido;
import com.example.Parcial2Arq.Repository.EquipoRepository;
import com.example.Parcial2Arq.Repository.PartidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;

    public PartidoService(PartidoRepository partidoRepository, EquipoRepository equipoRepository) {
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
    }

    public Partido crearPartido(Partido partido, Long idEquipoLocal, Long idEquipoVisita) {
        Equipo equipoLocal = equipoRepository.findById(idEquipoLocal)
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado con id: " + idEquipoLocal));

        Equipo equipoVisita = equipoRepository.findById(idEquipoVisita)
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado con id: " + idEquipoVisita));

        if (equipoLocal.equals(equipoVisita)) {
            throw new RuntimeException("Un equipo no puede jugar contra sí mismo");
        }

        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisita(equipoVisita);

        return partidoRepository.save(partido);
    }

    public Partido actualizarPartido(Long id, Partido partidoActualizado) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));

        partido.setFecha(partidoActualizado.getFecha());
        partido.setEstadio(partidoActualizado.getEstadio());
        partido.setGolesLocal(partidoActualizado.getGolesLocal());
        partido.setGolesVisita(partidoActualizado.getGolesVisita());

        return partidoRepository.save(partido);
    }

    @Transactional(readOnly = true)
    public List<Partido> obtenerTodosPartidos() {
        return partidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Partido obtenerPartidoPorId(Long id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
    }

    public void eliminarPartido(Long id) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));

        partidoRepository.delete(partido);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Objects>> obtenerPartidosConNombresEquipos() {
        return partidoRepository.findAllPartidosConNombresEquipos().stream()
                .map(row -> Map.of(
                        "id", row[0],
                        "fecha", row[1],
                        "estadio", row[2],
                        "equipoLocal", row[3],
                        "golesLocal", row[4],
                        "equipoVisita", row[5],
                        "golesVisita", row[6]
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public Integer obtenerTotalGolesEquipo(Long idEquipo) {
        return partidoRepository.getTotalGolesByEquipo(idEquipo);
    }

    @Transactional(readOnly = true)
    public List<Partido> buscarPartidosPorFecha(String fecha) {
        return partidoRepository.findByFecha(LocalDate.parse(fecha));
    }
}
