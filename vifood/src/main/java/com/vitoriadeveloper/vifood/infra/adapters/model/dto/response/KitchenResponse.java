package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.UUID;

public record KitchenResponse(
        UUID id,
        String nome
){}