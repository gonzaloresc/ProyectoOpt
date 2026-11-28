package com.proyecto.tienda_online.controller;

import com.proyecto.tienda_online.dto.UsuarioDto;
import com.proyecto.tienda_online.dto.UsuarioModificarDto;
import com.proyecto.tienda_online.service.UsuarioActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/me")
public class UsuarioController {

    @Autowired
    UsuarioActualService usuarioActualService;

    @GetMapping()
    public ResponseEntity<UsuarioDto> datosUsuario(){
        UsuarioDto usuarioDto = usuarioActualService.obtenerUsuarioActual();
        return ResponseEntity.ok(usuarioDto);
    }

    @PutMapping()
    public ResponseEntity<UsuarioDto> modificarNombreDireccionUsuario(@RequestBody UsuarioModificarDto usuarioModificarDto){
        UsuarioDto usuarioDto = usuarioActualService.modificarNombreDireccionUsuarioActual(usuarioModificarDto);
        return ResponseEntity.ok(usuarioDto);
    }
}
