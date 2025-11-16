package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepositoryPort {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
    Restaurant save(Restaurant body);
    void delete(Long id);
}
