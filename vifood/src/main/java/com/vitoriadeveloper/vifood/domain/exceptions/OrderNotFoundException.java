package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends BusinessException {

    public OrderNotFoundException(UUID id) {
        super("Pedido de id: " + id + " não encontrado!");
    }
}
