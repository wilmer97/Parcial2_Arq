package com.example.Parcial2Arq.Controller;

import com.example.Parcial2Arq.Model.Entrenador;
import com.example.Parcial2Arq.Service.EntrenadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping("/equipo/{idEquipo}")
    public ResponseEntity<Entrenador> crearEntrenador(
            @PathVariable Long idEquipo,
            @RequestBody Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorService.crearEntrenador(entrenador, idEquipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(nuevoEntrenador.getIdEntrenador())
                .toUri();
        return ResponseEntity.created(location).body(nuevoEntrenador);
    }

    @GetMapping
    public ResponseEntity<List<Entrenador>> obtenerTodosEntrenadores() {
        return ResponseEntity.ok(entrenadorService.obtenerTodosEntrenadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entrenadorService.obtenerEntrenadorPorId(id));
    }

    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<List<Entrenador>> buscarEntrenadoresPorEquipo(@PathVariable Long idEquipo) {
        return ResponseEntity.ok(entrenadorService.buscarEntrenadoresPorEquipo(idEquipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(
            @PathVariable Long id,
            @RequestBody Entrenador entrenadorActualizado) {
        return ResponseEntity.ok(entrenadorService.actualizarEntrenador(id, entrenadorActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Entrenador>> buscarEntrenadoresPorEspecialidad(
            @PathVariable String especialidad) {
        return ResponseEntity.ok(entrenadorService.buscarEntrenadoresPorEspecialidad(especialidad));
    }
}
