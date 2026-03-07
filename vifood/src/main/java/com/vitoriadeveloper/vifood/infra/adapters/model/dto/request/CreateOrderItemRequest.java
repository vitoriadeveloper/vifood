package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOrderItemRequest(
        @NotNull
        UUID productId,

        @NotNull
        Integer quantidade,
        String observacao
) {
}
