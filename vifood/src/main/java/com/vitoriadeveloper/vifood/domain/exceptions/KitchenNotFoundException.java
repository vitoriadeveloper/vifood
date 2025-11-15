package com.vitoriadeveloper.vifood.domain.exceptions;

public class KitchenNotFoundException extends RuntimeException {

    public KitchenNotFoundException(Long id) {
        super("Cozinha não encontrada: " + id);
    }
}
