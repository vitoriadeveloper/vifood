package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank
        String nome,

        @Email
        String email,

        @Min(6)
        @NotNull
        String senha

) {
}
