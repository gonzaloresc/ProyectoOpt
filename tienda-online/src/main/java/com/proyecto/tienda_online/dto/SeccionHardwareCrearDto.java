package com.proyecto.tienda_online.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SeccionHardwareCrearDto {
    private String nombre;
    private String descripcion;

    public SeccionHardwareCrearDto() {
    }

    public SeccionHardwareCrearDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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
}
