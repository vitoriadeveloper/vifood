package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class StateNotFoundException extends RuntimeException {

    public StateNotFoundException(UUID id) {
        super("Estado de id: " + id + " não encontrado!");
    }
}
