package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;
import com.vitoriadeveloper.vifood.domain.ports.out.IStatisticsRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StatisticsJpaAdapter implements IStatisticsRepositoryPort {
    private final CustomStatisticsQueryAdapter repository;
    @Override
    public List<DailySales> getDailySalesByRestaurantId(StatisticsFilter filters) {
        return repository.getDailySalesByRestaurantId(filters);
    }
}
