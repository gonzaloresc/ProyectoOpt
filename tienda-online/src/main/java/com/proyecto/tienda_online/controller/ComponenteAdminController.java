package com.proyecto.tienda_online.controller;

import com.proyecto.tienda_online.dto.ComponenteCrearDto;
import com.proyecto.tienda_online.dto.ComponenteDetalleDto;
import com.proyecto.tienda_online.dto.StockDto;
import com.proyecto.tienda_online.service.ComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin/componentes")
public class ComponenteAdminController {

    @Autowired
    private ComponenteService componenteService;

    @GetMapping
    public ResponseEntity<ArrayList<ComponenteDetalleDto>> listarComponentes(){
        ArrayList<ComponenteDetalleDto> listado = componenteService.listarTodosComponentesAdmin();
        return ResponseEntity.ok(listado);
    }

    // Le pongo valor por defecto
    @GetMapping("/stock-bajo")
    public ResponseEntity<ArrayList<ComponenteDetalleDto>> listarStockBajo(@RequestParam(defaultValue = "5") Integer umbral) {
        ArrayList<ComponenteDetalleDto> listado = componenteService.listarStockBajo(umbral);
        return ResponseEntity.ok(listado);
    }

    @PostMapping
    public ResponseEntity<ComponenteDetalleDto> crearComponente(@RequestBody ComponenteCrearDto dto){
        ComponenteDetalleDto creado = componenteService.crearComponente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponenteDetalleDto> modificarComponente(@PathVariable Long id, @RequestBody ComponenteCrearDto dto) {
        ComponenteDetalleDto modificado = componenteService.modificarComponente(id, dto);
        return ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> retirarComponente(@PathVariable Long id) {
        componenteService.retirarComponente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<StockDto> modificarStock(@PathVariable Long id, @RequestBody StockDto stockDto){
        StockDto stockA = componenteService.modificarStock(id, stockDto);
        return ResponseEntity.ok(stockA);
    }

    @PutMapping("/{id}/reactivar")
    public ResponseEntity<ComponenteDetalleDto> reactivarComponente(@PathVariable Long id){
        ComponenteDetalleDto reactivado = componenteService.reactivarComponente(id);
        return ResponseEntity.ok(reactivado);
    }

}
