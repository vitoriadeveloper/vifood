package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.UUID;

public record StateResponse(
        UUID id,
        String nome,
        String sigla
) {
}
