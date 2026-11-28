package com.proyecto.tienda_online.dto;

import com.proyecto.tienda_online.model.Rol;

import java.time.LocalDate;

public class UsuarioDto {
    private Long id;
    private String email;
    private String nombre;
    private String direccionEnvio;
    private Rol rol;
    private LocalDate fechaAlta;

    public UsuarioDto() {
    }

    public UsuarioDto(Long id, String email, String nombre, String direccionEnvio, Rol rol, LocalDate fechaAlta) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.direccionEnvio = direccionEnvio;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
