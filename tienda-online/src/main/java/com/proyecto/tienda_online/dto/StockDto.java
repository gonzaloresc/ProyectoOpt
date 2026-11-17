package com.proyecto.tienda_online.dto;

public class StockDto {
    private String sku;
    private String nombre;
    private Integer stock;

    public StockDto() {
    }

    public StockDto(String sku, String nombre, Integer stock) {
        this.sku = sku;
        this.nombre = nombre;
        this.stock = stock;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
