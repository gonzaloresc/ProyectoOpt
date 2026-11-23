package com.proyecto.tienda_online.controller;

import com.proyecto.tienda_online.dto.SeccionHardwareDto;
import com.proyecto.tienda_online.service.SeccionHardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/secciones")
public class SeccionHardwareController {
    @Autowired
    private SeccionHardwareService seccionHardwareService;

    @GetMapping
    public ResponseEntity<ArrayList<SeccionHardwareDto>> listarSeccionesHardware(){
        ArrayList<SeccionHardwareDto> seccionesHardwareDtos = seccionHardwareService.listarSeccionesHardware();
        return ResponseEntity.ok(seccionesHardwareDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeccionHardwareDto> consultarSeccion(@PathVariable Long id){
        SeccionHardwareDto seccionHardwareDto = seccionHardwareService.obtenerSeccionHardwarePorId(id);
        return ResponseEntity.ok(seccionHardwareDto);
    }

}
