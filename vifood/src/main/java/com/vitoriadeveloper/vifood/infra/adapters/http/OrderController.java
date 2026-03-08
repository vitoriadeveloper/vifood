package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.OrderService;
import com.vitoriadeveloper.vifood.domain.filters.OrderFilter;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.CreateOrderRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.UpdateOrderRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.*;
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
        var order = OrderMapper.toDomain(request);
        var orderSaved = service.create(order);
        return OrderMapper.toOrderResponse(orderSaved);
    }

    @GetMapping("/{pedidoId}")
    public OrderResponse findById(@PathVariable UUID pedidoId) {
        var order = service.findById(pedidoId);
        return OrderMapper.toOrderResponse(order);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<OrderSummaryClientResponse> findByClientId(@PathVariable UUID clienteId) {
        var orders = service.findByClientId(clienteId);
        return orders.stream().map(OrderMapper::toOrderSummaryClientResponse).toList();
    }

    @GetMapping("/restaurante/{restauranteId}")
    public List<OrderSummaryRestaurantResponse> findByRestaurantId(@PathVariable UUID restauranteId) {
        var orders = service.findByRestaurantId(restauranteId);
        return orders.stream().map(OrderMapper::toOrderSummaryRestaurantResponse).toList();
    }

    @PatchMapping("/{pedidoId}")
    public OrderResponse update(@PathVariable UUID pedidoId, @Valid @RequestBody UpdateOrderRequest request) {
        var order = OrderMapper.toDomain(request);
        var orderUpdated = service.updateById(pedidoId, order);
        return OrderMapper.toOrderResponse(orderUpdated);
    }

    @DeleteMapping("/{pedidoId}")
    public void delete(@PathVariable UUID pedidoId) {
        service.deleteById(pedidoId);
    }

    // #TODO ver necessidade de controller
    @PutMapping("/{pedidoId}/confirmar")
    public OrderResponse confirm(@PathVariable UUID pedidoId) {
        var order = service.findById(pedidoId);
        var orderConfirmed = service.confirm(order);
        return OrderMapper.toOrderResponse(orderConfirmed);
    }

    @PutMapping("/{pedidoId}/cancelar")
    public OrderResponse cancel(@PathVariable UUID pedidoId) {
        var order = service.findById(pedidoId);
        var orderCanceled = service.cancel(order);
        return OrderMapper.toOrderResponse(orderCanceled);
    }

    @PatchMapping("/{pedidoId}/status")
    public OrderResponse changeStatus(@PathVariable UUID pedidoId, @RequestParam OrderStatus status) {
        var order = service.findById(pedidoId);
        var orderUpdated = service.changeStatus(order, status);
        return OrderMapper.toOrderResponse(orderUpdated);
    }

    @GetMapping("/status")
    public List<OrderSummaryByStatus> findByStatus(@RequestParam OrderStatus status) {
        var orders = service.findByStatus(status);
        return orders.stream().map(OrderMapper::toOrderSummaryByStatus).toList();
    }

    @GetMapping("/pesquisar")
    public List<OrderFilterSummaryResponse> search(OrderFilter filters) {
        var orders = service.findByFilter(filters);
        return orders.stream().map(OrderMapper::toOrderFilterSummaryResponse).toList();
    }
}
