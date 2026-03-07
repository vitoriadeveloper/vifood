package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.OrderNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.*;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
import com.vitoriadeveloper.vifood.domain.ports.in.IOrderUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IOrderRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class OrderService implements IOrderUseCasePort {
    private final IOrderRepositoryPort repository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final ProductService productService;


    @Override
    public Order findById(UUID id) throws OrderNotFoundException {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Order create(Order request) {
        Restaurant restaurant = restaurantService.findById(request.getRestaurante().getId());
        User user = userService.findById(request.getCliente().getId());

        Order order = new Order();
        order.setRestaurante(restaurant);
        order.setCliente(user);
        order.setEnderecoEntrega(request.getEnderecoEntrega());

        for (OrderItem item : request.getItens()) {
            Product product = productService.findById(item.getProduto().getId());

            OrderItem orderItem = new OrderItem();
            orderItem.setProduto(product);
            orderItem.setQuantidade(item.getQuantidade());
            orderItem.setPrecoUnitario(product.getPreco());
            orderItem.calculateTotal();
            order.addItem(orderItem);
        }

        order.calculateTotalValue();
        return repository.save(order);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) throws OrderNotFoundException {
        var order = findById(id);
        repository.delete(order);
    }

    @Transactional
    @Override
    public Order updateById(UUID id, Order request) throws OrderNotFoundException {
        var existingOrder = findById(id);
        if (request.getEnderecoEntrega() != null) {
            existingOrder.setEnderecoEntrega(request.getEnderecoEntrega());
        }

        if (!request.getItens().isEmpty()) {

            existingOrder.getItens().clear();

            for (OrderItem item : request.getItens()) {

                Product product = productService.findById(item.getProduto().getId());

                OrderItem orderItem = new OrderItem();
                orderItem.setProduto(product);
                orderItem.setQuantidade(item.getQuantidade());
                orderItem.setPrecoUnitario(product.getPreco());
                orderItem.setObservacao(item.getObservacao());

                orderItem.calculateTotal();

                existingOrder.addItem(orderItem);
            }
        }

        existingOrder.calculateTotalValue();

        return repository.save(existingOrder);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Order> findByClientId(UUID clientId) {
        return repository.findByClientId(clientId);
    }

    @Override
    public List<Order> findByRestaurantId(UUID restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    @Transactional
    @Override
    public Order confirm(Order orderId) throws OrderNotFoundException {
        Order order = findById(orderId.getId());
        order.confirmOrder();
        return repository.save(order);
    }

    @Transactional
    @Override
    public Order cancel(Order orderId) throws OrderNotFoundException {
        Order order = findById(orderId.getId());
        order.cancelOrder();
        return repository.save(order);
    }

    @Override
    public Order changeStatus(Order orderId, OrderStatus newStatus) throws OrderNotFoundException {
        Order order = findById(orderId.getId());
        order.changeStatus(newStatus);
        return repository.save(order);
    }
}
