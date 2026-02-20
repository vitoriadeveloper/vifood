package com.vitoriadeveloper.vifood.application.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.domain.ports.in.IRestaurantUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantUseCasePort {
    private final IRestaurantRepositoryPort repository;
    private final IKitchenRepositoryPort kitchenRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Transactional
    @Override
    public Restaurant create(Restaurant body) {
        UUID kitchenId = body.getCozinha().getId();
        var kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));

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
        if (body.getEndereco() != null) {
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
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        var restaurant = repository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        // faz conversao de tipos automatico, sem isso teria apenas object
        Restaurant restaurantConverted = objectMapper.convertValue(fields, Restaurant.class);

        // percorrendo campos
        fields.forEach((fieldName, fieldValue) -> {
            // procura dentro da classe restaurant um atributo com aquele nome exato
            Field field = ReflectionUtils.findField(Restaurant.class, fieldName);

            if (field == null) {
                throw new IllegalArgumentException("Campo inválido " + fieldName);
            }
            // permite acessar campos privados
            field.setAccessible(true);

            // lê o valor convertido corretamente ex json veio "taxaFrete":12.5 -> BigDecimal(12.50)
            Object newValue = ReflectionUtils.getField(field, restaurantConverted);

            // pega o valor novo e sera direto no objeto que veio do banco de forma dinamica
            ReflectionUtils.setField(field, restaurant, newValue);
        });
        updateById(id, restaurant);
    }

    @Override
    public List<Restaurant> findByFilter(RestaurantFilter filter) {
        return repository.findByFilter(filter);
    }
}
