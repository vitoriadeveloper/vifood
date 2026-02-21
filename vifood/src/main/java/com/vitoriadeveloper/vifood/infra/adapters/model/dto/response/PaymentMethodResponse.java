package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.UUID;

public record PaymentMethodResponse(
        UUID id,
        String descricao
) {
}
