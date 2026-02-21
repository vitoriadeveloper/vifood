package com.vitoriadeveloper.vifood.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.vifood.domain.exceptions.CityNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Address;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.domain.ports.in.IRestaurantUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.ICityRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantUseCasePort {
    private final IRestaurantRepositoryPort repository;
    private final IKitchenRepositoryPort kitchenRepository;
    private final ICityRepositoryPort cityRepository;

    @Transactional
    @Override
    public Restaurant create(Restaurant body) {
        UUID kitchenId = body.getCozinha().getId();
        var kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));

        if (body.getEndereco() != null &&
                body.getEndereco().getCidade() != null && body.getEndereco().getCidade().getId() != null) {

            UUID cityId = body.getEndereco()
                    .getCidade().getId();

            var city = cityRepository.findById(cityId)
                    .orElseThrow(() -> new CityNotFoundException(cityId));

            body.getEndereco().setCidade(city);
        }


        body.setCozinha(kitchen);
        return repository.save(body);
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant findById(UUID id) throws RestaurantNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    @Override
    public Restaurant updateById(UUID id, Restaurant body) throws RestaurantNotFoundException, KitchenNotFoundException {
        var restaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        UUID kitchenId = body.getCozinha().getId();
        var kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));

        restaurant.setNome(body.getNome());
        restaurant.setTaxaFrete(body.getTaxaFrete());
        restaurant.setAtivo(body.getAtivo());
        restaurant.setAberto(body.getAberto());
        restaurant.setCozinha(kitchen);

        if (body.getFormasPagamento() != null) {
            restaurant.setFormasPagamento(body.getFormasPagamento());
        }
        if (body.getEndereco() != null && body.getEndereco().getCidade() != null && body.getEndereco().getCidade().getId() != null) {
            var city = cityRepository.findById(body.getEndereco().getCidade().getId())
                    .orElseThrow(() -> new CityNotFoundException(body.getEndereco().getCidade().getId()));

            body.getEndereco().setCidade(city);
            restaurant.setEndereco(body.getEndereco());
        }

        return repository.save(restaurant);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        var restaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        repository.delete(id);
    }

    @Transactional
    @Override
    public void updatePartial(UUID id, Map<String, Object> fields) {

        var restaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        fields.forEach((fieldName, fieldValue) -> {

            switch (fieldName) {

                case "nome" -> restaurant.setNome((String) fieldValue);

                case "taxaFrete" ->
                        restaurant.setTaxaFrete(
                                new BigDecimal(fieldValue.toString())
                        );

                case "ativo" ->
                        restaurant.setAtivo((Boolean) fieldValue);

                case "aberto" ->
                        restaurant.setAberto((Boolean) fieldValue);

                case "cozinhaId" -> {
                    UUID kitchenId = UUID.fromString(fieldValue.toString());

                    var kitchen = kitchenRepository.findById(kitchenId)
                            .orElseThrow(() -> new KitchenNotFoundException(kitchenId));

                    restaurant.setCozinha(kitchen);
                }

                case "endereco" -> {
                    Map<String, Object> enderecoMap = (Map<String, Object>) fieldValue;

                    Address endereco = restaurant.getEndereco();
                    if (endereco == null) {
                        endereco = new Address();
                    }

                    if (enderecoMap.containsKey("cep")) {
                        endereco.setCep((String) enderecoMap.get("cep"));
                    }

                    if (enderecoMap.containsKey("cidadeId")) {
                        UUID cityId = UUID.fromString(enderecoMap.get("cidadeId").toString());

                        var city = cityRepository.findById(cityId)
                                .orElseThrow(() -> new CityNotFoundException(cityId));

                        endereco.setCidade(city);
                    }

                    restaurant.setEndereco(endereco);
                }

                default -> throw new IllegalArgumentException("Campo inválido: " + fieldName);
            }
        });

        repository.save(restaurant);
    }
    @Override
    public List<Restaurant> findByFilter(RestaurantFilter filter) {
        return repository.findByFilter(filter);
    }

    @Override
    public void activate(UUID id) {
        repository.activate(id);
    }

    @Override
    public void inactivate(UUID id) {
        repository.inactivate(id);
    }
}
