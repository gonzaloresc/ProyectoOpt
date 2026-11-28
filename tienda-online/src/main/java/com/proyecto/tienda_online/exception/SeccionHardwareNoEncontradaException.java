package com.proyecto.tienda_online.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeccionHardwareNoEncontradaException extends ResponseStatusException {
    public SeccionHardwareNoEncontradaException(String message) {
        super(HttpStatus.NOT_FOUND , message);
    }
}
