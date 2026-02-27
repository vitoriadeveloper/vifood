package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class StateNotFoundException extends BusinessException {

    public StateNotFoundException(UUID id) {
        super("Estado de id: " + id + " não encontrado!");
    }
}
