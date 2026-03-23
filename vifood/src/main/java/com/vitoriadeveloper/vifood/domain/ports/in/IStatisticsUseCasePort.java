package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;

import java.util.UUID;

public interface IStatisticsUseCasePort {
    Object getDailySales(UUID restaurantId, String dataInicio, String dataFim) throws RestaurantNotFoundException;
}
