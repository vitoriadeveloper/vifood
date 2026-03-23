package com.vitoriadeveloper.vifood.domain.ports.out;

import java.util.UUID;

public interface IStatisticsRepositoryPort {
        void getDailySalesByRestaurantId(UUID restaurantId, String dataInicio, String dataFim);
}
