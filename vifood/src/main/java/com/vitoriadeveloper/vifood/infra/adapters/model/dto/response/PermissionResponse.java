package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.UUID;

public record PermissionResponse(
        String name,
        UUID id,
        String descricao
) {
}
