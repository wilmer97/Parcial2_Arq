package com.example.Parcial2Arq.Model;

import java.time.LocalDate;

public class Partido {

    private int id_partido;
    private LocalDate fecha;
    private String estadio;
    private int eqp_local;
    private int eqo_visita;
    private int gol_local;
    private int gol_visita;

    public Partido() {
    }

    public Partido(int id_partido, LocalDate fecha, String estadio, int eqp_local, int eqo_visita, int gol_local, int gol_visita) {
        this.id_partido = id_partido;
        this.fecha = fecha;
        this.estadio = estadio;
        this.eqp_local = eqp_local;
        this.eqo_visita = eqo_visita;
        this.gol_local = gol_local;
        this.gol_visita = gol_visita;
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getEqp_local() {
        return eqp_local;
    }

    public void setEqp_local(int eqp_local) {
        this.eqp_local = eqp_local;
    }

    public int getEqo_visita() {
        return eqo_visita;
    }

    public void setEqo_visita(int eqo_visita) {
        this.eqo_visita = eqo_visita;
    }

    public int getGol_local() {
        return gol_local;
    }

    public void setGol_local(int gol_local) {
        this.gol_local = gol_local;
    }

    public int getGol_visita() {
        return gol_visita;
    }

    public void setGol_visita(int gol_visita) {
        this.gol_visita = gol_visita;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id_partido=" + id_partido +
                ", fecha=" + fecha +
                ", estadio='" + estadio + '\'' +
                ", eqp_local=" + eqp_local +
                ", eqo_visita=" + eqo_visita +
                ", gol_local=" + gol_local +
                ", gol_visita=" + gol_visita +
                '}';
    }
}
