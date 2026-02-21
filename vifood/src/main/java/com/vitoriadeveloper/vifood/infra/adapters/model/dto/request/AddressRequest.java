package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressRequest(
        @NotBlank
        String cep,

        String logradouro,

        @NotBlank
        String numero,

        String complemento,

        String bairro,

        @NotNull
        UUID cidadeId
) {
}
