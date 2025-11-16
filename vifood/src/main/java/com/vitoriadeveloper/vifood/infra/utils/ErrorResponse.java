package com.vitoriadeveloper.vifood.infra.utils;

import java.time.OffsetDateTime;

public record ErrorResponse(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message
) {}
