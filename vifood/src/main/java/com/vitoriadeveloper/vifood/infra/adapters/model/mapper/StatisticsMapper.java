package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.StatisticsResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class StatisticsMapper {

    public static StatisticsFilter toDomain(UUID restaurantId, LocalDate dataInicio, LocalDate dataFim) {
        return new StatisticsFilter(restaurantId, dataInicio, dataFim);
    }

    public static StatisticsResponse toResponse(DailySales dailySales) {
        return new StatisticsResponse(dailySales.getData(), dailySales.getTotalVendas(), dailySales.getTotalFaturado());
    }

    public static List<StatisticsResponse> toResponseList(List<DailySales> dailySales) {
        return dailySales.stream()
                .map(StatisticsMapper::toResponse)
                .toList();
    }
}
