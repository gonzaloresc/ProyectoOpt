package com.proyecto.tienda_online.controller;

import com.proyecto.tienda_online.dto.UsuarioDto;
import com.proyecto.tienda_online.dto.UsuarioModificarDto;
import com.proyecto.tienda_online.service.UsuarioActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/usuarios")
public class UsuarioAdminController {

    @Autowired
    UsuarioActualService usuarioActualService;

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
        List<UsuarioDto> usuariosDto = usuarioActualService.listarUsuarios();
        return ResponseEntity.ok(usuariosDto);
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<UsuarioDto> cambiarRolUsuario(@PathVariable Long id, @RequestBody UsuarioModificarDto usuarioModificarDto){
        UsuarioDto usuarioDto = usuarioActualService.modificarRolUsuario(id, usuarioModificarDto);
        return ResponseEntity.ok(usuarioDto);

    }

}
