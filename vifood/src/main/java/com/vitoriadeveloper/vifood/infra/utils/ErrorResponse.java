package com.vitoriadeveloper.vifood.infra.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        List<Field> fields
) {

    @Builder
    public record Field(
            String name,
            String userMessage) {
    }
}
