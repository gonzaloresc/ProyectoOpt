package com.proyecto.tienda_online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeccionHardwareNoVaciaException extends ResponseStatusException {
    public SeccionHardwareNoVaciaException(String message) {
        super(HttpStatus.CONFLICT , message);
    }
}
