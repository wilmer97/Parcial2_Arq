package com.example.Parcial2Arq.Service;

import com.example.Parcial2Arq.Model.Equipo;
import com.example.Parcial2Arq.Repository.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public Equipo crearEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo actualizarEquipo(Long id, Equipo equipoActualizado) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
        equipo.setNombre(equipoActualizado.getNombre());
        equipo.setCiudad(equipoActualizado.getCiudad());
        equipo.setFundacion(equipoActualizado.getFundacion());

        return equipoRepository.save(equipo);
    }

    @Transactional(readOnly = true)
    public List<Equipo> obtenerTodosEquipos() {
        return equipoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id:" + id));
    }

    public void eliminarEquipo(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));

        equipoRepository.delete(equipo);
    }

    @Transactional(readOnly = true)
    public List<Equipo> buscarEquiposPorCiudad(String ciudad) {
        return equipoRepository.findByCiudad(ciudad);
    }
}
