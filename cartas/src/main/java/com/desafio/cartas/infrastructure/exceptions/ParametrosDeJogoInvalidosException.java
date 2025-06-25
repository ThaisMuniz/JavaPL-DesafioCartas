package com.desafio.cartas.infrastructure.exceptions;

import java.util.Map;

public class ParametrosDeJogoInvalidosException extends RuntimeException {

    private final Map<String, String> errors;

    public ParametrosDeJogoInvalidosException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
