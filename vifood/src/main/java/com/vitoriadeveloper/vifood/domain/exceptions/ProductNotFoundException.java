package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(UUID id) {
        super("Produto de id: " + id + " não encontrado!");
    }
}
