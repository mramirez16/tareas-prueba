package com.tareas.app.tareas_app.Models;

import java.time.LocalDate;

/**
 * Modelo tarea para el mapp de la base de datos
 */
public class TareaModel {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "TareaModel [nombre=" + nombre + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + "]";
    }

}
