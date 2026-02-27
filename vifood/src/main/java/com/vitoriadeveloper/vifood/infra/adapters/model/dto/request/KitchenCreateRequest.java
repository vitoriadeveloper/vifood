package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KitchenCreateRequest(

        @NotBlank
        String nome
) {
}
