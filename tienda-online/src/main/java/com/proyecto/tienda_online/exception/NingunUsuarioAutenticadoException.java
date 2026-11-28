package com.proyecto.tienda_online.exception;

import org.apache.http.client.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NingunUsuarioAutenticadoException extends ResponseStatusException {
    public NingunUsuarioAutenticadoException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
