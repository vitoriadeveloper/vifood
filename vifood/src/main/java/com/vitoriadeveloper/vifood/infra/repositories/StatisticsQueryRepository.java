package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;

import java.util.List;

public interface StatisticsQueryRepository {
        List<DailySales> getDailySalesByRestaurantId(StatisticsFilter filters);
}
