package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.OrderItem;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.ItemsResponse;

import java.util.List;

public class ItemsMapper {

    public static List<ItemsResponse> toItemsResponse(List<OrderItem> items){
        return items.stream()
                .map(item -> new ItemsResponse(
                        item.getQuantidade(),
                        item.getPrecoTotal(),
                        item.getPrecoUnitario(),
                        ProductMapper.toProductSummaryResponse(item.getProduto())
                )).toList();
    }
}
