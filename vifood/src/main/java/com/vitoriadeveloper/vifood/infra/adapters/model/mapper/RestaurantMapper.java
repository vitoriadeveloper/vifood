package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.*;
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
                restaurant.getDataAtualizacao(),
                restaurant.getEndereco() != null
                        ? AddressMapper.toResponse(restaurant.getEndereco())
                        : null
        );
    }

    public static Restaurant toDomain(RestaurantRequest request) {
        var kitchen = new Kitchen();
        kitchen.setId(request.cozinhaId());

        Address address = getAddress(request);

        Restaurant restaurant = new Restaurant();
        restaurant.setNome(request.nome());
        restaurant.setTaxaFrete(request.taxaFrete());
        restaurant.setAtivo(request.ativo());
        restaurant.setAberto(request.aberto());
        restaurant.setCozinha(kitchen);
        restaurant.setEndereco(address);

        return restaurant;
    }

    private static Address getAddress(RestaurantRequest request) {

        Address address = new Address();
        address.setCep(request.endereco().cep());
        address.setLogradouro(request.endereco().logradouro());
        address.setNumero(request.endereco().numero());
        address.setComplemento(request.endereco().complemento());
        address.setBairro(request.endereco().bairro());

        if(request.endereco().cidadeId() != null) {
            var city = new City();
            city.setId(request.endereco().cidadeId());
            address.setCidade(city);
        }

        return address;
    }
}
