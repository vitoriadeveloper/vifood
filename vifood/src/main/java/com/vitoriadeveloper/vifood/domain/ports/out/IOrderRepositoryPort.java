package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderRepositoryPort {
    Optional<Order> findById(UUID id);

    List<Order> findAll();

    Order save(Order order);

    void delete(Order order);
}
