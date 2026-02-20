package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class KitchenNotFoundException extends RuntimeException {

    public KitchenNotFoundException(UUID id) {
        super("Cozinha de id: " + id + " não encontrada!");
    }
}
