package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record RestaurantRequest(
        @NotBlank
        String nome,

        @NotNull
        @PositiveOrZero
        BigDecimal taxaFrete,

        @NotNull
        Boolean ativo,

        @NotNull
        Boolean aberto,

        @NotNull
        UUID cozinhaId,

        @NotNull
        @Valid
        AddressRequest endereco

) {
}
