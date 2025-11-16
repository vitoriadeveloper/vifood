package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.domain.ports.in.IRestaurantUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantUseCasePort {
    private final IRestaurantRepositoryPort repository;
    private final IKitchenRepositoryPort kitchenRepository;

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

    @Override
    public void deleteById(Long id) {
        var restaurantId = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        repository.delete(id);
    }
}
