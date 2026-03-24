package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StatisticsResponse(LocalDate data,
                                 Long totalVendas,
                                 BigDecimal faturamentoTotal
) {
}
