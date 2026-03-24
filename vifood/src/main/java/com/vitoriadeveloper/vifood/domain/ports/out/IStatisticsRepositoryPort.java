package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;

import java.util.List;

public interface IStatisticsRepositoryPort  {
    List<DailySales> getDailySalesByRestaurantId(StatisticsFilter filters);
}
