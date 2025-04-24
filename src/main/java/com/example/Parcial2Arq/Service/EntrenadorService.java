package com.example.Parcial2Arq.Service;

import com.example.Parcial2Arq.Model.Entrenador;
import com.example.Parcial2Arq.Model.Equipo;
import com.example.Parcial2Arq.Repository.EntrenadorRepository;
import com.example.Parcial2Arq.Repository.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final EquipoRepository equipoRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository, EquipoRepository equipoRepository) {
        this.entrenadorRepository = entrenadorRepository;
        this.equipoRepository = equipoRepository;
    }

    public Entrenador crearEntrenador(Entrenador entrenador, Long idEquipo) {
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + idEquipo));

        entrenador.setEquipo(equipo);
        return entrenadorRepository.save(entrenador);
    }

    public Entrenador actualizarEntrenador(Long id, Entrenador entrenadorActualizado) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));

        entrenador.setNombre(entrenadorActualizado.getNombre());
        entrenador.setEspecialidad(entrenadorActualizado.getEspecialidad());

        return entrenadorRepository.save(entrenador);
    }

    @Transactional(readOnly = true)
    public List<Entrenador> obtenerTodosEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Entrenador obtenerEntrenadorPorId(Long id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    public void eliminarEntrenador(Long id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));

        entrenadorRepository.delete(entrenador);
    }

    @Transactional(readOnly = true)
    public List<Entrenador> buscarEntrenadoresPorEspecialidad(String especialidad) {
        return entrenadorRepository.findByEspecialidad(especialidad);
    }

    @Transactional(readOnly = true)
    public List<Entrenador> buscarEntrenadoresPorEquipo(Long idEquipo) {
        return entrenadorRepository.findByEquipoIdEquipo(idEquipo);
    }
}
