package com.example.Parcial2Arq.Controller;

import com.example.Parcial2Arq.Model.Equipo;
import com.example.Parcial2Arq.Service.EquipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoService.crearEquipo(equipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoEquipo.getIdEquipo())
                .toUri();
        return ResponseEntity.created(location).body(nuevoEquipo);
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosEquipos() {
        return ResponseEntity.ok(equipoService.obtenerTodosEquipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipoService.obtenerEquipoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(
            @PathVariable Long id,
            @RequestBody Equipo equipoActualizado) {
        return ResponseEntity.ok(equipoService.actualizarEquipo(id, equipoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Equipo>> buscarEquiposPorCiudad(@PathVariable String ciudad) {
        return ResponseEntity.ok(equipoService.buscarEquiposPorCiudad(ciudad));
    }

}
