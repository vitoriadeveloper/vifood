package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.filters.OrderFilter;
import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.domain.model.PaginationRequest;
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

    Pagination<Order> findByFilter(OrderFilter filter, PaginationRequest paginationRequest);

}
