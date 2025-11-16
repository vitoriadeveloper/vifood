package com.vitoriadeveloper.vifood.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.domain.ports.in.IRestaurantUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantUseCasePort {
    private final IRestaurantRepositoryPort repository;
    private final IKitchenRepositoryPort kitchenRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Transactional
    @Override
    public Restaurant create(Restaurant body) {
        Long kitchenId = body.getCozinha().getId();
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
    public Restaurant findById(Long id) throws RestaurantNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    @Override
    public Restaurant updateById(Long id, Restaurant body) throws RestaurantNotFoundException, KitchenNotFoundException {
        var restaurantId = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        Long kitchenId = body.getCozinha().getId();
        var kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));

        body.setId(id);
        body.setCozinha(kitchen);
        return repository.save(body);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var restaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        repository.delete(id);
    }

    @Transactional
    @Override
    public void updatePartial(Long id, Map<String, Object> fields) {
        var restaurant = repository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        Restaurant restaurantConverted = objectMapper.convertValue(fields, Restaurant.class);

        fields.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, fieldName);

            if (field == null) {
                throw new IllegalArgumentException("Campo inválido " + fieldName);
            }
            field.setAccessible(true);
            try {
                Object newValue = field.get(restaurantConverted);

                ReflectionUtils.setField(field, restaurant, newValue);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Erro ao aplicar PATCH no campo " + fieldName);
            }
        });
        updateById(id, restaurant);
    }
}
