package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String nome,
        String email,
        OffsetDateTime dataCadastro,
        List<GroupPermissionResponse> permissoes
) {
}
