package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record OrderSummaryClientResponse(
        UUID id,
        OffsetDateTime dataPedido,
        BigDecimal valorTotal,
        OrderStatus status
) {
}
