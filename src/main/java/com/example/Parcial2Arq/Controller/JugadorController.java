package com.example.Parcial2Arq.Controller;

import com.example.Parcial2Arq.Model.Jugador;
import com.example.Parcial2Arq.Service.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping("/equipo/{idEquipo}")
    public ResponseEntity<Jugador> crearJugador(
            @PathVariable Long idEquipo,
            @RequestBody Jugador jugador) {
        Jugador nuevoJugador = jugadorService.crearJugador(jugador, idEquipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(nuevoJugador.getIdJugador())
                .toUri();
        return ResponseEntity.created(location).body(nuevoJugador);
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> obtenerTodosJugadores() {
        return ResponseEntity.ok(jugadorService.obtenerTodosJugadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jugadorService.obtenerJugadorPorId(id));
    }

    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<List<Jugador>> obtenerJugadoresPorEquipo(@PathVariable Long idEquipo) {
        return ResponseEntity.ok(jugadorService.obtenerJugadoresPorEquipo(idEquipo));
    }

    @GetMapping("/goles")
    public ResponseEntity<List<Jugador>> obtenerJugadoresConMasGolesQue(
            @RequestParam(name = "min", defaultValue = "5") int minGoles) {
        return ResponseEntity.ok(jugadorService.obtenerJugadoresConMasGolesQue(minGoles));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizarJugador(
            @PathVariable Long id,
            @RequestBody Jugador jugadorActualizado) {
        return ResponseEntity.ok(jugadorService.actualizarJugador(id, jugadorActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posicion/{posicion}")
    public ResponseEntity<List<Jugador>> buscarJugadoresPorPosicion(@PathVariable String posicion) {
        return ResponseEntity.ok(jugadorService.buscarJugadoresPorPosicion(posicion));
    }
}
