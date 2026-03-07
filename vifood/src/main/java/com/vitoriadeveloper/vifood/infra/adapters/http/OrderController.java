package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.OrderService;
import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.CreateOrderRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.UpdateOrderRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.OrderResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        Order order = OrderMapper.toDomain(request);
        Order orderSaved = service.create(order);
        return OrderMapper.toOrderResponse(orderSaved);
    }

    @GetMapping("/{pedidoId}")
    public OrderResponse findById(@PathVariable UUID pedidoId) {
        Order order = service.findById(pedidoId);
        return OrderMapper.toOrderResponse(order);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<OrderResponse> findByClientId(@PathVariable UUID clienteId) {
        List<Order> orders = service.findByClientId(clienteId);
        return orders.stream().map(OrderMapper::toOrderResponse).toList();
    }

    @GetMapping("/restaurante/{restauranteId}")
    public List<OrderResponse> findByRestaurantId(@PathVariable UUID restauranteId) {
        List<Order> orders = service.findByRestaurantId(restauranteId);
        return orders.stream().map(OrderMapper::toOrderResponse).toList();
    }

    @PatchMapping("/{pedidoId}")
    public OrderResponse update(@PathVariable UUID pedidoId, @Valid @RequestBody UpdateOrderRequest request) {
        Order order = OrderMapper.toDomain(request);
        Order orderUpdated = service.updateById(pedidoId, order);
        return OrderMapper.toOrderResponse(orderUpdated);
    }

    @DeleteMapping("/{pedidoId}")
    public void delete(@PathVariable UUID pedidoId) {
        service.deleteById(pedidoId);
    }

    @PutMapping("/{pedidoId}/confirmar")
    public OrderResponse confirm(@PathVariable UUID pedidoId) {
        Order order = service.findById(pedidoId);
        Order orderConfirmed = service.confirm(order);
        return OrderMapper.toOrderResponse(orderConfirmed);
    }

    @PutMapping("/{pedidoId}/cancelar")
    public OrderResponse cancel(@PathVariable UUID pedidoId) {
        Order order = service.findById(pedidoId);
        Order orderCanceled = service.cancel(order);
        return OrderMapper.toOrderResponse(orderCanceled);
    }

    @PatchMapping("/{pedidoId}/status")
    public OrderResponse changeStatus(@PathVariable UUID pedidoId, @RequestParam OrderStatus status) {
        Order order = service.findById(pedidoId);
        Order orderUpdated = service.changeStatus(order, status);
        return OrderMapper.toOrderResponse(orderUpdated);
    }

    @GetMapping("/status")
    public List<OrderResponse> findByStatus(@RequestParam OrderStatus status) {
        List<Order> orders = service.findByStatus(status);
        return orders.stream().map(OrderMapper::toOrderResponse).toList();
    }
}
