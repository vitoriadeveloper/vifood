    package com.vitoriadeveloper.vifood.infra.adapters.repositories;

    import com.vitoriadeveloper.vifood.domain.model.Order;
    import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
    import com.vitoriadeveloper.vifood.domain.ports.out.IOrderRepositoryPort;
    import com.vitoriadeveloper.vifood.infra.repositories.OrderRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Component;

    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

    @Component
    @RequiredArgsConstructor
    public class OrderJpaAdapter implements IOrderRepositoryPort {
        private final OrderRepository repository;

        @Override
        public Optional<Order> findById(UUID id) {
            return repository.findById(id);
        }

        @Override
        public List<Order> findAll() {
            return repository.findAll();
        }

        @Override
        public Order save(Order order) {
            return repository.save(order);
        }

        @Override
        public void delete(Order order) {
            repository.delete(order);
        }

        @Override
        public List<Order> findByClientId(UUID clientId) {
            return repository.findByClienteId(clientId);
        }

        @Override
        public List<Order> findByStatus(OrderStatus status) {
            return repository.findByStatus(status);
        }

        @Override
        public List<Order> findByRestaurantId(UUID restaurantId) {
            return repository.findByRestauranteId(restaurantId);
        }

    }