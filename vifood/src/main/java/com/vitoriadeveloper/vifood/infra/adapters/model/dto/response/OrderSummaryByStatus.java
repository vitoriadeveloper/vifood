package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record OrderSummaryByStatus(
        UUID pedidoId,
        OffsetDateTime dataPedido,
        OrderStatus status,
        RestaurantSummaryResponse restaurante
) {
}
