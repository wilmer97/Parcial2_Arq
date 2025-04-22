package com.example.Parcial2Arq.Model;

public class Entrenador {

    private int id_entrenador;
    private String nombre;
    private String especilidad;
    private int id_equipo;

    public Entrenador() {
    }

    public Entrenador(int id_entrenador, String nombre, String especilidad, int id_equipo) {
        this.id_entrenador = id_entrenador;
        this.nombre = nombre;
        this.especilidad = especilidad;
        this.id_equipo = id_equipo;
    }

    public int getId_entrenador() {
        return id_entrenador;
    }

    public void setId_entrenador(int id_entrenador) {
        this.id_entrenador = id_entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecilidad() {
        return especilidad;
    }

    public void setEspecilidad(String especilidad) {
        this.especilidad = especilidad;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "id_entrenador=" + id_entrenador +
                ", nombre='" + nombre + '\'' +
                ", especilidad='" + especilidad + '\'' +
                ", id_equipo=" + id_equipo +
                '}';
    }
}
