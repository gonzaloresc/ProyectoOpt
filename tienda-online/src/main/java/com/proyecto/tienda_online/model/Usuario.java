package com.proyecto.tienda_online.model;

import java.time.LocalDate;

public class Usuario {

    private Long id;
    private String uid;
    private String email;
    private String nombre;
    private String direccionEnvio;
    private Rol rol;
    private LocalDate fechaAlta;

    public Usuario() {}

    public Usuario(Long id, String uid, String email, String nombre, String direccionEnvio, Rol rol, LocalDate fechaAlta) {
        this.id = id;
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
