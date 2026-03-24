package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record CreateStatisticsRequest(
        UUID restauranteId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
