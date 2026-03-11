package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        @NotNull
        UUID restauranteId,
        @NotNull
        UUID clienteId,
        @Valid
        AddressCreateRequest enderecoEntrega,
        @Valid
        List<CreateOrderItemRequest> itens

) {
}
