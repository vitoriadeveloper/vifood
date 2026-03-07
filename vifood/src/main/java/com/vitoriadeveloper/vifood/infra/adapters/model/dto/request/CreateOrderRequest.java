package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        @NotNull
        UUID restaurantId,
        @NotNull
        UUID clientId,
        @Valid
        AddressCreateRequest enderecoEntrega,
        @Valid
        List<CreateOrderItemRequest> itens

) {
}
