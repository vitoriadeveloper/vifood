package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRestaurantRepositoryPort {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(UUID id);
    Restaurant save(Restaurant body);
    void delete(UUID id);
    List<Restaurant> findByFilter(RestaurantFilter filter);
    void activate(UUID id);
    void inactivate(UUID id);
    void associatePaymentMethod(UUID restaurantId, UUID paymentMethodId);
    void disassociatePaymentMethod(UUID restaurantId, UUID paymentMethodId);

}
