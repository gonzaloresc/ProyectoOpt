package com.proyecto.tienda_online.dto;

public class ComponenteResumenDto {
    private String sku;
    private String nombre;

    public ComponenteResumenDto() {
    }

    public ComponenteResumenDto(String sku, String nombre) {
        this.sku = sku;
        this.nombre = nombre;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
