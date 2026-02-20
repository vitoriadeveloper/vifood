package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.KitchenResponse;

import java.util.List;

public class KitchenResponseMapper {

    public static KitchenResponse toResponse(Kitchen kitchen) {
        return new KitchenResponse(
                kitchen.getId(),
                kitchen.getNome()
        );
    }

    public static List<KitchenResponse> toCollectionResponse(List<Kitchen> kitchens) {
        return kitchens.stream().map(KitchenResponseMapper::toResponse).toList();
    }
}
