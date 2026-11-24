package com.proyecto.tienda_online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ComponenteNoEncontradoException extends ResponseStatusException {
    public ComponenteNoEncontradoException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}