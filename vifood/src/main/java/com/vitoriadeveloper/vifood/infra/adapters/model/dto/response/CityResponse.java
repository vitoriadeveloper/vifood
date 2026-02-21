package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.UUID;

public record CityResponse(
        UUID id,
        String nome,
        StateResponse estado
) {
}
