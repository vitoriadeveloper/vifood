package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import java.util.UUID;

public record CityCreateRequest(
        String nome,
        UUID estadoId
) {
}
