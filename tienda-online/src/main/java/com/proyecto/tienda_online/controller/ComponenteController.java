package com.proyecto.tienda_online.controller;

import com.proyecto.tienda_online.dto.ComponenteDetalleDto;
import com.proyecto.tienda_online.dto.ComponenteFiltroDto;
import com.proyecto.tienda_online.dto.ComponenteResumenDto;
import com.proyecto.tienda_online.service.ComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/componentes")
public class ComponenteController {


    @Autowired
    private ComponenteService componenteService;

    @GetMapping
    public ResponseEntity<ArrayList<ComponenteResumenDto>> listarComponentes(ComponenteFiltroDto filtro) {
        ArrayList<ComponenteResumenDto> listado = componenteService.listarComponentesFiltrados(filtro);
        return ResponseEntity.ok(listado);
    }

    @GetMapping("/destacados")
    public ResponseEntity<ArrayList<ComponenteResumenDto>> listarDestacados() {
        ArrayList<ComponenteResumenDto> destacados = componenteService.listarComponentesDestacados();
        return ResponseEntity.ok(destacados);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ComponenteDetalleDto> consultarPorSku(@PathVariable String sku) {
        ComponenteDetalleDto detalle = componenteService.obtenerComponenteActivoPorSku(sku);
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponenteDetalleDto> consultarComponentes(@PathVariable Long id) {
        ComponenteDetalleDto detalle = componenteService.obtenerComponenteActivoPorId(id);
        return ResponseEntity.ok(detalle);
    }

}
