package com.proyecto.tienda_online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeccionHardwareYaExistenteException extends ResponseStatusException {
    public SeccionHardwareYaExistenteException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
