package com.example.Parcial2Arq.Controller;

import com.example.Parcial2Arq.Model.Partido;
import com.example.Parcial2Arq.Service.PartidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @PostMapping("/equipos/{idLocal}/{idVisita}")
    public ResponseEntity<Partido> crearPartido(
            @PathVariable Long idLocal,
            @PathVariable Long idVisita,
            @RequestBody Partido partido) {
        Partido nuevoPartido = partidoService.crearPartido(partido, idLocal, idVisita);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(nuevoPartido.getIdPartido())
                .toUri();
        return ResponseEntity.created(location).body(nuevoPartido);
    }

    @GetMapping
    public ResponseEntity<List<Partido>> obtenerTodosPartidos() {
        return ResponseEntity.ok(partidoService.obtenerTodosPartidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partido> obtenerPartidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.obtenerPartidoPorId(id));
    }

    @GetMapping("/detallados")
    public ResponseEntity<List<Map<String, Objects>>> obtenerPartidosConNombresEquipos() {
        return ResponseEntity.ok(partidoService.obtenerPartidosConNombresEquipos());
    }

    @GetMapping("/equipo/{idEquipo}/goles")
    public ResponseEntity<Integer> obtenerTotalGolesEquipo(@PathVariable Long idEquipo) {
        return ResponseEntity.ok(partidoService.obtenerTotalGolesEquipo(idEquipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> actualizarPartido(
            @PathVariable Long id,
            @RequestBody Partido partidoActualizado) {
        return ResponseEntity.ok(partidoService.actualizarPartido(id, partidoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id) {
        partidoService.eliminarPartido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Partido>> buscarPartidosPorFecha(@PathVariable String fecha) {
        return ResponseEntity.ok(partidoService.buscarPartidosPorFecha(fecha));
    }
}
