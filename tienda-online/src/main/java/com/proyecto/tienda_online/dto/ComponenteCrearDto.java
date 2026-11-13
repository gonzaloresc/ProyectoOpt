package com.proyecto.tienda_online.dto;

import com.proyecto.tienda_online.model.SeccionHardware;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComponenteCrearDto {
    private String sku;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private Long seccionHardwareId;
    private String descripcion;
    private String imagenUrl;
    private Boolean activo;
    private Boolean destacado;
    private LocalDate fechaAlta;
    private SeccionHardware categoria;

    public ComponenteCrearDto() {
    }

    public ComponenteCrearDto(String sku, String nombre, BigDecimal precio, Integer stock, Long seccionHardwareId, String descripcion, String imagenUrl, Boolean activo, Boolean destacado, LocalDate fechaAlta, SeccionHardware categoria) {
        this.sku = sku;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.seccionHardwareId = seccionHardwareId;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.destacado = destacado;
        this.categoria = categoria;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getSeccionHardwareId() {
        return seccionHardwareId;
    }

    public void setSeccionHardwareId(Long seccionHardwareId) {
        this.seccionHardwareId = seccionHardwareId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public SeccionHardware getCategoria() {
        return categoria;
    }

    public void setCategoria(SeccionHardware categoria) {
        this.categoria = categoria;
    }
}
