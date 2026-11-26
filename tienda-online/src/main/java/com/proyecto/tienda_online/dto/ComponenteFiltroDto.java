package com.proyecto.tienda_online.dto;

import com.proyecto.tienda_online.model.Direccion;
import com.proyecto.tienda_online.model.Orden;

import java.math.BigDecimal;

public class ComponenteFiltroDto {

    private Long categoriaId;
    private String q;
    private BigDecimal precioMin;
    private BigDecimal precioMax;
    private Boolean soloDisponibles;
    private Orden orden;
    private Direccion direccion;
    // Apartado propio:
    private String marca;

    public ComponenteFiltroDto() {}

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public BigDecimal getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(BigDecimal precioMin) {
        this.precioMin = precioMin;
    }

    public BigDecimal getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(BigDecimal precioMax) {
        this.precioMax = precioMax;
    }

    public Boolean getSoloDisponibles() {
        return soloDisponibles;
    }

    public void setSoloDisponibles(Boolean soloDisponibles) {
        this.soloDisponibles = soloDisponibles;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
