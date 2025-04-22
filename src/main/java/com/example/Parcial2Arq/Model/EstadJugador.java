package com.example.Parcial2Arq.Model;

public class EstadJugador {

    private int id_estadisticas;
    private int id_jugador;
    private int id_partido;
    private int min_jugados;
    private int goles;
    private int asistencias;
    private int tarjetasAmarrilla;
    private int tarjetasRojas;

    public EstadJugador() {
    }

    public EstadJugador(int id_estadisticas, int id_jugador, int id_partido, int min_jugados, int goles, int asistencias, int tarjetasAmarrilla, int tarjetasRojas) {
        this.id_estadisticas = id_estadisticas;
        this.id_jugador = id_jugador;
        this.id_partido = id_partido;
        this.min_jugados = min_jugados;
        this.goles = goles;
        this.asistencias = asistencias;
        this.tarjetasAmarrilla = tarjetasAmarrilla;
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getId_estadisticas() {
        return id_estadisticas;
    }

    public void setId_estadisticas(int id_estadisticas) {
        this.id_estadisticas = id_estadisticas;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getMin_jugados() {
        return min_jugados;
    }

    public void setMin_jugados(int min_jugados) {
        this.min_jugados = min_jugados;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getTarjetasAmarrilla() {
        return tarjetasAmarrilla;
    }

    public void setTarjetasAmarrilla(int tarjetasAmarrilla) {
        this.tarjetasAmarrilla = tarjetasAmarrilla;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    @Override
    public String toString() {
        return "EstadJugador{" +
                "id_estadisticas=" + id_estadisticas +
                ", id_jugador=" + id_jugador +
                ", id_partido=" + id_partido +
                ", min_jugados=" + min_jugados +
                ", goles=" + goles +
                ", asistencias=" + asistencias +
                ", tarjetasAmarrilla=" + tarjetasAmarrilla +
                ", tarjetasRojas=" + tarjetasRojas +
                '}';
    }
}
