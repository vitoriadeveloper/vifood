package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean ativo,
        RestaurantSummaryResponse restaurante
) {
}
