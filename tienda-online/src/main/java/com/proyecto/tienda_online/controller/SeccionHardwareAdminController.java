package com.proyecto.tienda_online.controller;

import com.proyecto.tienda_online.dto.SeccionHardwareCrearDto;
import com.proyecto.tienda_online.dto.SeccionHardwareDto;
import com.proyecto.tienda_online.service.SeccionHardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin/secciones")
public class SeccionHardwareAdminController {


    @Autowired
    private SeccionHardwareService seccionHardwareService;

    @GetMapping
    public ResponseEntity<ArrayList<SeccionHardwareDto>> listarSeccionesHardware(){
        ArrayList<SeccionHardwareDto> seccionesHardwareDtos = seccionHardwareService.listarSeccionesHardware();
        return ResponseEntity.ok(seccionesHardwareDtos);
    }

    @PostMapping
    public ResponseEntity<SeccionHardwareDto> crearSeccionHardware(@RequestBody SeccionHardwareCrearDto seccionHardwareCrearDto){
        SeccionHardwareDto seccionHardwareDto = seccionHardwareService.crearSeccionHardware(seccionHardwareCrearDto);
        return ResponseEntity.ok(seccionHardwareDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<SeccionHardwareDto> modificarSeccionHardware(@PathVariable Long id ,@RequestBody SeccionHardwareCrearDto seccionHardwareCrearDto){
        SeccionHardwareDto seccionHardwareDto = seccionHardwareService.modificarSeccionHardware(id ,seccionHardwareCrearDto);
        return ResponseEntity.ok(seccionHardwareDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SeccionHardwareDto> eliminarSeccionHardware(@PathVariable Long id){
        SeccionHardwareDto seccionHardwareDto = seccionHardwareService.eliminarSeccionHardware(id);
        return ResponseEntity.ok(seccionHardwareDto);
    }

    //TODO Delete sin dependencia circular

}
