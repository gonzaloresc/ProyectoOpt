package com.proyecto.tienda_online.config;

import com.proyecto.tienda_online.security.FiltroAutenticacionFirebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final FiltroAutenticacionFirebase filtroFirebase;
    public SecurityConfig(FiltroAutenticacionFirebase filtroFirebase) {
        this.filtroFirebase = filtroFirebase;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable()) // No es necesario en una API REST

                .sessionManagement(session -> session // Cada petición se autentica mediante su propio token
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth // Definimos qué rutas necesitan permisos
                        .requestMatchers("/api/admin", "/api/admin/**") // Solo los administradores pueden acceder
                        .hasRole("ADMIN")
                        .anyRequest().permitAll() // El resto de rutas son públicas
                )
                .addFilterBefore(filtroFirebase, UsernamePasswordAuthenticationFilter.class); // Añadimos el filtro de Firebase
        return http.build();
    }

    // Evita que Spring registre el filtro dos veces
    @Bean
    public FilterRegistrationBean<FiltroAutenticacionFirebase> registroFiltroFirebase() {
        FilterRegistrationBean<FiltroAutenticacionFirebase> registro = new FilterRegistrationBean<>();
        registro.setFilter(filtroFirebase);
        registro.setEnabled(false);
        return registro;
    }
}