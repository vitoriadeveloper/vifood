package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.List;
import java.util.UUID;

public record GroupPermissionResponse(
        String name,
        UUID id,
        List<PermissionResponse> permissoes
) {
}
