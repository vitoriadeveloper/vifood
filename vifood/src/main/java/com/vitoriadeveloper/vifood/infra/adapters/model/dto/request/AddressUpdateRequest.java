package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressUpdateRequest(
        String cep,

        String logradouro,

        String numero,

        String complemento,

        String bairro,

        @NotNull
        UUID cidadeId
) {
}
