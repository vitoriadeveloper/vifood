package com.vitoriadeveloper.vifood.infra.exceptions;

import java.time.OffsetDateTime;

public record ErrorResponse(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message
) {}
