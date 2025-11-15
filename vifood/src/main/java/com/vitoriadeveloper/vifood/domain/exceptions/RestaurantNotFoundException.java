package com.vitoriadeveloper.vifood.domain.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {
        super("Restaurante de id: " + id + " não encontrado!");
    }
}
