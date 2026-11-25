package com.proyecto.tienda_online.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.proyecto.tienda_online.model.Usuario;
import com.proyecto.tienda_online.service.UsuarioActualService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class FiltroAutenticacionFirebase extends OncePerRequestFilter {

    @Autowired
    UsuarioActualService usuarioActualService;

    private final FirebaseAuth firebaseAuth;
    public FiltroAutenticacionFirebase(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Obtener la cabecera Authorization
        String authorizationHeader = request.getHeader("Authorization");

        // Comprobar que existe y utiliza Bearer
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el token
        String token = authorizationHeader.substring(7);

        // Verificar el token con Firebase
        try {
            FirebaseToken tokenVerificado = firebaseAuth.verifyIdToken(token);
            Usuario usuario = usuarioActualService.obtenerOCrearDesdeToken(tokenVerificado);
            SimpleGrantedAuthority permisos = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name());

            // Crear la autenticación para Spring Security
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(usuario, null, List.of(permisos));

            // Guardar la autenticación en el contexto
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Token no válido o caducado"
            );
            return;
        }

        // Continuar con la petición
        filterChain.doFilter(request, response);
    }
}

