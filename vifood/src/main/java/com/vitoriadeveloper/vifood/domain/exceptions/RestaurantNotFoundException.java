package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class RestaurantNotFoundException extends BusinessException {

    public RestaurantNotFoundException(UUID id) {
        super("Restaurante de id: " + id + " não encontrado!");
    }
}
