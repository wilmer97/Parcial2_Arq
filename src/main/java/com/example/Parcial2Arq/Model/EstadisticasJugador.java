package com.example.Parcial2Arq.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "estadisticas_jugador")
public class EstadisticasJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadisticas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @Column(name = "minutos_jugados", nullable = false)
    private Integer minutosJugados = 0;

    @Column(name = "goles", nullable = false)
    private Integer goles = 0;

    @Column(name = "asistencias", nullable = false)
    private Integer asistencias = 0;

    @Column(name = "tarjetas_amarillas", nullable = false)
    private Integer tarjetas_amarillas = 0;

    @Column(name = "tarjetas_rojas", nullable = false)
    private Integer tarjetas_Rojas = 0;

    public EstadisticasJugador() {
    }

    public EstadisticasJugador(Long idEstadisticas, Jugador jugador, Partido partido, Integer minutosJugados, Integer goles, Integer asistencias, Integer tarjetas_amarillas, Integer tarjetas_Rojas) {
        this.idEstadisticas = idEstadisticas;
        this.jugador = jugador;
        this.partido = partido;
        this.minutosJugados = minutosJugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.tarjetas_amarillas = tarjetas_amarillas;
        this.tarjetas_Rojas = tarjetas_Rojas;
    }

    public Long getIdEstadisticas() {
        return idEstadisticas;
    }

    public void setIdEstadisticas(Long idEstadisticas) {
        this.idEstadisticas = idEstadisticas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Integer getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(Integer minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public Integer getGoles() {
        return goles;
    }

    public void setGoles(Integer goles) {
        this.goles = goles;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public Integer getTarjetas_amarillas() {
        return tarjetas_amarillas;
    }

    public void setTarjetas_amarillas(Integer tarjetas_amarillas) {
        this.tarjetas_amarillas = tarjetas_amarillas;
    }

    public Integer getTarjetas_Rojas() {
        return tarjetas_Rojas;
    }

    public void setTarjetas_Rojas(Integer tarjetas_Rojas) {
        this.tarjetas_Rojas = tarjetas_Rojas;
    }
}
