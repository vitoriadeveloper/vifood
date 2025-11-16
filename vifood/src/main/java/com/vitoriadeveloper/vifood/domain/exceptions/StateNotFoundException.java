package com.vitoriadeveloper.vifood.domain.exceptions;

public class StateNotFoundException extends RuntimeException {

    public StateNotFoundException(Long id) {
        super("Estado de id: " + id + " não encontrado!");
    }
}
