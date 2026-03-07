package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.math.BigDecimal;

public record ItemsResponse(
        Integer quantidade,
        BigDecimal precoTotal,
        BigDecimal precoUnitario,
        ProductSummaryResponse produto

) {
}
