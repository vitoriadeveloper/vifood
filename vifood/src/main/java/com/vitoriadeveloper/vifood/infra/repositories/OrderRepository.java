package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, UUID>{
    List<Order> findByClienteId(UUID clientId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByRestauranteId(UUID restaurantId);
}
