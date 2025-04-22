package com.example.Parcial2Arq.Model;

import java.time.LocalDate;

public class Jugador {

    private int id_jugador;
    private String nombre;
    private String posicion;
    private String dorsal;
    private LocalDate fecha_nac;
    private String nacionalidad;
    private int id_equipo;

    public Jugador() {
    }

    public Jugador(int id_jugador, String nombre, String posicion, String dorsal, LocalDate fecha_nac, String nacionalidad, int id_equipo) {
        this.id_jugador = id_jugador;
        this.nombre = nombre;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.id_equipo = id_equipo;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id_jugador=" + id_jugador +
                ", nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                ", dorsal='" + dorsal + '\'' +
                ", fecha_nac=" + fecha_nac +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", id_equipo=" + id_equipo +
                '}';
    }
}
