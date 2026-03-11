package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.*;

public record UserCreateRequest(
        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @Size(min = 6, max = 12)
        @NotNull
        String senha

) {
}
