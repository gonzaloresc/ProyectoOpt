package com.proyecto.tienda_online.dto;

import com.proyecto.tienda_online.model.Rol;

public class UsuarioModificarDto {
    private String nombre;
    private String direccionEnvio;
    private String rol;

    public UsuarioModificarDto() {
    }

    public UsuarioModificarDto(String nombre, String direccionEnvio, String rol) {
        this.nombre = nombre;
        this.direccionEnvio = direccionEnvio;
        this.rol = rol;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
