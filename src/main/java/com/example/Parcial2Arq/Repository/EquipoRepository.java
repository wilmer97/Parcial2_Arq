package com.example.Parcial2Arq.Repository;

import com.example.Parcial2Arq.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByCiudad(String ciudad);
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}
