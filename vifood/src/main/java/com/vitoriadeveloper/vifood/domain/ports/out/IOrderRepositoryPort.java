package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.filters.OrderFilter;
import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderRepositoryPort {
    Optional<Order> findById(UUID id);

    List<Order> findAll();

    Order save(Order order);

    void delete(Order order);

    List<Order> findByClientId(UUID clientId);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByRestaurantId(UUID restaurantId);

    List<Order> findByFilter(OrderFilter filter);

}
