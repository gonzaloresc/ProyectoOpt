package com.proyecto.tienda_online.service;

import com.google.firebase.auth.FirebaseToken;
import com.proyecto.tienda_online.model.Rol;
import com.proyecto.tienda_online.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UsuarioActualService {
    private final Set<String> correosAdmins;
    private final Map<String, Usuario> usuariosPorUid = new ConcurrentHashMap<>();
    private long siguienteId = 0;

    public UsuarioActualService(@Value("${tienda.admins:}") String adminsConfig) {
        this.correosAdmins = parsearAdmins(adminsConfig);
    }

    public Usuario obtenerOCrearDesdeToken(FirebaseToken tokenVerificado) {
        String uid = tokenVerificado.getUid();
        String email = asegurarEmail(tokenVerificado.getEmail());

        return usuariosPorUid.computeIfAbsent(uid, id -> crearUsuarioInicial(id, email)); // Si el uid está en el Hashmap devuelve el Usuario de la lista. Si no, crea el usuario, lo añade a la lista y lo devuelve
    }

    public Usuario obtenerUsuarioActual() {
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();  // Obtienes la autenticación de FiltroAutenticacionFirebase
        if (autenticacion == null || !(autenticacion.getPrincipal() instanceof Usuario usuario)) { // Compruebas si hay un usuario en el contexto actual
            throw new IllegalStateException("No hay un usuario autenticado");
        }
        return usuario;
    }

    private Usuario crearUsuarioInicial(String uid, String email) {
        Rol rol = correosAdmins.contains(email) ? Rol.ADMIN : Rol.CLIENTE;
        String nombre = email.substring(0, email.indexOf("@"));
        LocalDate fechaAlta = LocalDate.now();

        return new Usuario(++siguienteId,uid, email, nombre, null , rol, fechaAlta);
    }

    private Set<String> parsearAdmins(String adminsConfig) {
        return Arrays.stream(adminsConfig.split(","))
                .map(this::asegurarEmail)
                .filter(email -> !email.isBlank())
                .collect(Collectors.toSet());
    }

    private String asegurarEmail(String email) {
        return email == null || !email.contains("@") ? "" : email.trim().toLowerCase();
    }

}