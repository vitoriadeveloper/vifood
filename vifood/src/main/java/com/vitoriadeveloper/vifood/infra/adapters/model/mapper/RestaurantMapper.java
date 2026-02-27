package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.*;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.AddressUpdateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantUpdateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantResponse;

import java.util.List;

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
                        : null,
                restaurant.getFormasPagamento() != null ? restaurant.getFormasPagamento()
                        .stream()
                        .map(PaymentMethodMapper::toResponse)
                        .toList() : List.of()
        );
    }

    public static Restaurant toDomain(RestaurantCreateRequest request) {
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

    private static Address getAddress(RestaurantCreateRequest request) {

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

    public static void merge(
            RestaurantUpdateRequest request,
            Restaurant restaurant
    ) {

        if (request.nome() != null) {
            restaurant.setNome(request.nome());
        }

        if (request.taxaFrete() != null) {
            restaurant.setTaxaFrete(request.taxaFrete());
        }

        if (request.ativo() != null) {
            restaurant.setAtivo(request.ativo());
        }

        if (request.aberto() != null) {
            restaurant.setAberto(request.aberto());
        }

        if (request.cozinhaId() != null) {
            var kitchen = new Kitchen();
            kitchen.setId(request.cozinhaId());
            restaurant.setCozinha(kitchen);
        }

        if (request.endereco() != null) {
            mergeAddress(request.endereco(), restaurant);
        }
    }

    private static void mergeAddress(
            AddressUpdateRequest request,
            Restaurant restaurant
    ) {

        Address address = restaurant.getEndereco();

        if (address == null) {
            address = new Address();
            restaurant.setEndereco(address);
        }

        if (request.cep() != null)
            address.setCep(request.cep());

        if (request.logradouro() != null)
            address.setLogradouro(request.logradouro());

        if (request.numero() != null)
            address.setNumero(request.numero());

        if (request.complemento() != null)
            address.setComplemento(request.complemento());

        if (request.bairro() != null)
            address.setBairro(request.bairro());

        if (request.cidadeId() != null) {
            var city = new City();
            city.setId(request.cidadeId());
            address.setCidade(city);
        }
    }
}
