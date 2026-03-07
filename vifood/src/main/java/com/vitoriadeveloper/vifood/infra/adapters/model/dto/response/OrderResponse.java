package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        OffsetDateTime dataPedido,
        BigDecimal valorTotal,
        String status,
        RestaurantSummaryResponse restaurante,
        ClientSummaryResponse cliente,
        AddressResponse enderecoEntrega,
        List<ItemsResponse> itens
) {
}
