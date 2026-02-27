package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        String nome,

        String descricao,

        BigDecimal preco
) {
}
