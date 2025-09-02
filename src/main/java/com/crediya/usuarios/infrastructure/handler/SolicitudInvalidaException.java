package com.crediya.usuarios.infrastructure.handler;

public class SolicitudInvalidaException extends RuntimeException {
    public SolicitudInvalidaException(String message) {
        super(message);
    }
}
