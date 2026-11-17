package com.proyecto.tienda_online.dto;

import com.proyecto.tienda_online.model.SeccionHardware;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComponenteDetalleDto {
    private Long id;
    private String sku;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String imagenUrl;
    private Boolean destacado;
    private LocalDate fechaAlta;

    // Devolvemos Dto en vez de model
    private SeccionHardwareDto categoria;

    // Atributos propios:
    private String marca;
    private Integer garantiaMeses;
}
