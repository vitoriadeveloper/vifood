package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.KitchenResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.PaginationResponse;

import java.util.List;

public class KitchenMapper {

    public static KitchenResponse toResponse(Kitchen kitchen) {
        return new KitchenResponse(
                kitchen.getId(),
                kitchen.getNome()
        );
    }

    public static List<KitchenResponse> toCollectionResponse(List<Kitchen> kitchens) {
        return kitchens.stream().map(KitchenMapper::toResponse).toList();
    }

    public static PaginationResponse<KitchenResponse> toCollectionResponse(Pagination<Kitchen> kitchens) {
        return new PaginationResponse<>(
            kitchens.content().stream().map(KitchenMapper::toResponse).toList(),
            kitchens.pageNumber(),
            kitchens.pageSize(),
            kitchens.totalElements(),
            kitchens.totalPages(),
            kitchens.first(),
            kitchens.last(),
            kitchens.hasNext(),
            kitchens.hasPrevious()
        );
    }
}

