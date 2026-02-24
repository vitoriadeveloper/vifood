package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID id) {
        super("Usuario de id: " + id + " não encontrado!");
    }
}
