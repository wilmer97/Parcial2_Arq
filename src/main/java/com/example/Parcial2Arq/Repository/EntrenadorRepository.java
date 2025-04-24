package com.example.Parcial2Arq.Repository;

import com.example.Parcial2Arq.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    List<Entrenador> findByEspecialidad(String especialidad);

    List<Entrenador> findByEquipoIdEquipo(Long idEquipo);

}
