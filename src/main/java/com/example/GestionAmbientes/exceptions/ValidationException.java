package com.example.GestionAmbientes.exceptions;

public class ValidationException extends RuntimeException {
    private final String regla;

    public ValidationException(String regla, String mensaje) {
        super(mensaje);
        this.regla = regla;
    }

    public String getRegla() {
        return regla;
    }
}