package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record ProductImageUpdateOrCreateRequest(
        @NotNull
        MultipartFile arquivo,

        @NotNull
        @Size(max = 40)
        String descricao
) {
}
