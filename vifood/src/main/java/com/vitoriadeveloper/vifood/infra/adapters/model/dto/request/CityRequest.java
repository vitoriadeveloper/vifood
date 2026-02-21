package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import java.util.UUID;

public record CityRequest(
        String nome,
        UUID estadoId
) {
}
