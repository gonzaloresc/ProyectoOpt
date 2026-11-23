package com.proyecto.tienda_online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AtributosInvalidosException extends ResponseStatusException {
    public AtributosInvalidosException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
