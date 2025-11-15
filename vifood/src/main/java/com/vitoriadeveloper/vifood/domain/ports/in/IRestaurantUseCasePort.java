package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantUseCasePort {
    Restaurant create(Restaurant body);
    List<Restaurant> findAll();
    Restaurant findById(Long id) throws RestaurantNotFoundException;
    Restaurant updateById(Long id, Restaurant body) throws RestaurantNotFoundException;
    void deleteById(Long id);
}
