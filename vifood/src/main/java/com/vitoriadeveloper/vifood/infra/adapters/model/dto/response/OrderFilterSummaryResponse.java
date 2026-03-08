package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderFilterSummaryResponse(
        UUID produtoId,
        BigDecimal valorTotal,
        RestaurantSummaryResponse restaurante,
        ClientSummaryResponse cliente,
        OrderStatus status
) {
}
