package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.Valid;
import java.util.List;

public record UpdateOrderRequest(
        @Valid
        AddressCreateRequest enderecoEntrega,
        @Valid
        List<CreateOrderItemRequest> itens
) {
}
