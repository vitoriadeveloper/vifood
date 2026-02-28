package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePermissionRequest(
        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 1, max = 100)
        String descricao
) {
}
