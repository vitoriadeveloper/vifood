package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.filters.OrderFilter;
import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.domain.model.PaginationRequest;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
import com.vitoriadeveloper.vifood.domain.ports.out.IOrderRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.OrderRepository;
import com.vitoriadeveloper.vifood.infra.repositories.spec.OrderSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderRepositoryPort {
    private final OrderRepository jpaRepository;

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return jpaRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        jpaRepository.delete(order);
    }

    @Override
    public List<Order> findByClientId(UUID clientId) {
        return jpaRepository.findByClienteId(clientId);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return jpaRepository.findByStatus(status);
    }

    @Override
    public List<Order> findByRestaurantId(UUID restaurantId) {
        return jpaRepository.findByRestauranteId(restaurantId);
    }

    @Override
    public Pagination<Order> findByFilter(OrderFilter filter, PaginationRequest paginationRequest) {
        var spec = new OrderSpecificationBuilder(filter);
        var pageable = PageRequest.of(
                paginationRequest.page(),
                paginationRequest.size()
        );

        var page = jpaRepository.findAll(spec, pageable);
        return new Pagination<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}