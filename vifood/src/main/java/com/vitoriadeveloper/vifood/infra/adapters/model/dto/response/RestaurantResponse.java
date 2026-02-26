package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record RestaurantResponse(
        UUID id,
        String nome,
        BigDecimal taxaFrete,
        Boolean ativo,
        Boolean aberto,
        KitchenResponse cozinha,
        OffsetDateTime dataCadastro,
        OffsetDateTime dataAtualizacao,
        AddressResponse endereco,
        List<PaymentMethodResponse> formasPagamento
) {
}
