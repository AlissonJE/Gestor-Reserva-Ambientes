package com.example.GestionAmbientes.exceptions;

public class BusinessConflictException extends RuntimeException {
    private final String regla;

    public BusinessConflictException(String regla, String mensaje) {
        super(mensaje);
        this.regla = regla;
    }

    public String getRegla() {
        return regla;
    }
}