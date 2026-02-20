package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(UUID id) {
        super("Restaurante de id: " + id + " não encontrado!");
    }
}
