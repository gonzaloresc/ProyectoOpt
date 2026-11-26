package com.proyecto.tienda_online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ComponenteInvalidoException extends ResponseStatusException {
    public ComponenteInvalidoException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
