package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

public record AddressResponse(
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            CityResponse cidade
) {
}
