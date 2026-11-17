package com.proyecto.tienda_online.dto;

import java.math.BigDecimal;

public class ComponenteResumenDto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String imagenUrl;
    private String nombreSeccionHardware;

    public ComponenteResumenDto() {
    }

    public ComponenteResumenDto(Long id, String nombre, BigDecimal precio, String imagenUrl, String nombreSeccionHardware) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.nombreSeccionHardware = nombreSeccionHardware;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getNombreSeccionHardware() {
        return nombreSeccionHardware;
    }

    public void setNombreSeccionHardware(String nombreSeccionHardware) {
        this.nombreSeccionHardware = nombreSeccionHardware;
    }
}
