package com.vitoriadeveloper.vifood.infra.adapters.model.dto.request;

import java.util.List;
import java.util.UUID;

public record RestaurantBatchRequest(
        List<UUID> restauranteIds
) {
}
