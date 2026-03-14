package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.OrderNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.OrderFilter;
import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.domain.model.PaginationRequest;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface IOrderUseCasePort {
    Order findById(UUID id) throws OrderNotFoundException;

    List<Order> findAll();

    Order create(Order order);

    void deleteById(UUID id) throws OrderNotFoundException;

    Order updateById(UUID id, Order order) throws OrderNotFoundException;

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByClientId(UUID clientId);

    List<Order> findByRestaurantId(UUID restaurantId);

    Order confirm(Order orderId) throws OrderNotFoundException;

    Order cancel(Order orderId) throws OrderNotFoundException;

    Order changeStatus(Order orderId, OrderStatus newStatus) throws OrderNotFoundException;

    Pagination<Order> findByFilter(OrderFilter filter, PaginationRequest page);
}
