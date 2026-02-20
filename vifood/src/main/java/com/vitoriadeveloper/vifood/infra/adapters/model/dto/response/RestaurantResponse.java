package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantResponse(
        UUID id,
        String nome,
        BigDecimal taxaFrete,
        Boolean ativo,
        Boolean aberto,
        KitchenResponse cozinha,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {
}
