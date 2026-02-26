package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class PaymentMethodNotFoundException extends RuntimeException {

    public PaymentMethodNotFoundException(UUID id) {
        super("Método de pagamento de id: " + id + " não encontrado!");
    }
}
