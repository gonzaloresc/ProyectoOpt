package com.proyecto.tienda_online.model;

public class LineaCarrito {

    private Componente componente;
    private Integer cantidad;

    public LineaCarrito() {}

    public LineaCarrito(Componente componente, Integer cantidad) {
        this.componente = componente;
        this.cantidad = cantidad;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
