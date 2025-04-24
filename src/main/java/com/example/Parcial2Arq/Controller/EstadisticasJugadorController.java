package com.example.Parcial2Arq.Controller;


import com.example.Parcial2Arq.Model.EstadisticasJugador;
import com.example.Parcial2Arq.Service.EstadisticasJugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasJugadorController {

    private final EstadisticasJugadorService estadisticasService;

    public EstadisticasJugadorController(EstadisticasJugadorService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @PostMapping("/jugador/{idJugador}/partido/{idPartido}")
    public ResponseEntity<EstadisticasJugador> crearEstadistica(
            @PathVariable Long idJugador,
            @PathVariable Long idPartido,
            @RequestBody EstadisticasJugador estadistica) {
        EstadisticasJugador nuevaEstadistica = estadisticasService.crearEstadistica(
                estadistica, idJugador, idPartido);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(nuevaEstadistica.getIdEstadisticas())
                .toUri();
        return ResponseEntity.created(location).body(nuevaEstadistica);
    }

    @GetMapping
    public ResponseEntity<List<EstadisticasJugador>> obtenerTodasEstadisticas() {
        return ResponseEntity.ok(estadisticasService.obtenerTodasEstadisticas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadisticasJugador> obtenerEstadisticaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estadisticasService.obtenerEstadisticaPorId(id));
    }

    @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<EstadisticasJugador>> obtenerEstadisticasPorJugador(
            @PathVariable Long idJugador) {
        return ResponseEntity.ok(estadisticasService.obtenerEstadisticasPorJugador(idJugador));
    }

    @GetMapping("/partido/{idPartido}")
    public ResponseEntity<List<EstadisticasJugador>> obtenerEstadisticasPorPartido(
            @PathVariable Long idPartido) {
        return ResponseEntity.ok(estadisticasService.obtenerEstadisticasPorPartido(idPartido));
    }

    @GetMapping("/jugador/{idJugador}/goles")
    public ResponseEntity<Integer> obtenerTotalGolesJugador(@PathVariable Long idJugador) {
        return ResponseEntity.ok(estadisticasService.obtenerTotalGolesJugador(idJugador));
    }

    @GetMapping("/jugador/{idJugador}/completas")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticasCompletasJugador(
            @PathVariable Long idJugador) {
        return ResponseEntity.ok(estadisticasService.obtenerEstadisticasCompletasJugador(idJugador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadisticasJugador> actualizarEstadistica(
            @PathVariable Long id,
            @RequestBody EstadisticasJugador estadisticaActualizada) {
        return ResponseEntity.ok(estadisticasService.actualizarEstadistica(id, estadisticaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadistica(@PathVariable Long id) {
        estadisticasService.eliminarEstadistica(id);
        return ResponseEntity.noContent().build();
    }
}
