package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends BusinessException {

    public ProductNotFoundException(UUID id) {
        super("Produto de id: " + id + " não encontrado!");
    }

        public ProductNotFoundException(UUID productId, UUID restaurantId) {
            super("Produto de id: " + productId + " não encontrado para o restaurante de id: " + restaurantId);
        }
}
