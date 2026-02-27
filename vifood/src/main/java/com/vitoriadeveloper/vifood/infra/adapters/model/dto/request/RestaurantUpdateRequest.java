package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record RestaurantUpdateRequest(
        String nome,

        @PositiveOrZero
        BigDecimal taxaFrete,

        Boolean ativo,

        Boolean aberto,

        UUID cozinhaId,


        @Valid
        AddressUpdateRequest endereco
) {
}
