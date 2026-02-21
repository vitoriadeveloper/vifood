package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.UUID;

public record GroupPermissionResponse(
        String name,
        UUID id
) {
}
