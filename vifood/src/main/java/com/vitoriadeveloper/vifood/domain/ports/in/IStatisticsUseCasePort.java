package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;

import java.util.List;

public interface IStatisticsUseCasePort {
    List<DailySales> getDailySales(StatisticsFilter filters) throws RestaurantNotFoundException;
}
