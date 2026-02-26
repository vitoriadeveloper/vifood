package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IRestaurantUseCasePort {
    Restaurant create(Restaurant body);
    List<Restaurant> findAll();
    Restaurant findById(UUID id) throws RestaurantNotFoundException;
    Restaurant updateById(UUID id, Restaurant body) throws RestaurantNotFoundException;
    void deleteById(UUID id);
    void updatePartial(UUID id, Map<String, Object> fields);
    List<Restaurant> findByFilter(RestaurantFilter filter);
    void activate(UUID id);
    void inactivate(UUID id);
    Restaurant associatePaymentMethod(UUID restaurantId, UUID paymentMethodId);
    Restaurant disassociatePaymentMethod(UUID restaurantId, UUID paymentMethodId);

}
