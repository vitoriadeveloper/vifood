package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(UUID id) {
        super("Cidade de id: " + id + " não encontrado!");
    }
}
