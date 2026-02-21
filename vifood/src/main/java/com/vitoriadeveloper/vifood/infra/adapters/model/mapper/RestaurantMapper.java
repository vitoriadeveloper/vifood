package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantResponse;

public class RestaurantMapper {

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getNome(),
                restaurant.getTaxaFrete(),
                restaurant.getAtivo(),
                restaurant.getAberto(),
                KitchenMapper.toResponse(restaurant.getCozinha()),
                restaurant.getDataCadastro(),
                restaurant.getDataAtualizacao()
        );
    }

    public static Restaurant toDomain(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();

        restaurant.setNome(request.nome());
        restaurant.setTaxaFrete(request.taxaFrete());
        restaurant.setAtivo(request.ativo());
        restaurant.setAberto(request.aberto());
        if (request.cozinhaId() != null) {
            Kitchen kitchen = new Kitchen();
            kitchen.setId(request.cozinhaId());
            restaurant.setCozinha(kitchen);
        }
        return restaurant;
    }
}
