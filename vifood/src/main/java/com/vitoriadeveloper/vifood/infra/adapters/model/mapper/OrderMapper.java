package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.*;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.CreateOrderRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.UpdateOrderRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.*;

public class OrderMapper {

    public static OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getDataPedido(),
                order.getValorTotal(),
                order.getStatus().name(),
                RestaurantMapper.toRestaurantSummaryResponse(order.getRestaurante()),
                UserMapper.toClientSummaryResponse(order.getCliente()),
                AddressMapper.toResponse(order.getEnderecoEntrega()),
                ItemsMapper.toItemsResponse(order.getItens())
        );
    }

    public static Order toDomain(CreateOrderRequest request) {
        Order order = new Order();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(request.restauranteId());
        User client = new User();
        client.setId(request.clienteId());

        order.setRestaurante(restaurant);
        order.setCliente(client);
        order.setEnderecoEntrega(AddressMapper.toDomain(request.enderecoEntrega()));

        request.itens().forEach(item -> {
            OrderItem itemOrder = new OrderItem();


            Product product = new Product();
            product.setId(item.produtoId());

            itemOrder.setProduto(product);
            itemOrder.setQuantidade(item.quantidade());
            itemOrder.setObservacao(item.observacao());

            order.addItem(itemOrder);
        });
        return order;

    }

    public static Order toDomain(UpdateOrderRequest request) {
        Order order = new Order();

        if (request.enderecoEntrega() != null) {
            order.setEnderecoEntrega(
                    AddressMapper.toDomain(request.enderecoEntrega())
            );
        }

        if (request.itens() != null) {
            request.itens().forEach(item -> {
                OrderItem orderItem = new OrderItem();

                Product product = new Product();
                product.setId(item.produtoId());

                orderItem.setProduto(product);
                orderItem.setQuantidade(item.quantidade());
                orderItem.setObservacao(item.observacao());

                order.addItem(orderItem);
            });
        }

        return order;
    }

    public static OrderSummaryClientResponse toOrderSummaryClientResponse(Order order) {
        return new OrderSummaryClientResponse(
                order.getId(),
                order.getDataPedido(),
                order.getValorTotal(),
                order.getStatus()
        );
    }

    public static  OrderSummaryRestaurantResponse toOrderSummaryRestaurantResponse(Order order) {
        return new OrderSummaryRestaurantResponse(
                order.getId(),
                order.getCliente().getNome()
        );
    }

    public static OrderSummaryByStatus toOrderSummaryByStatus(Order order) {
        return new OrderSummaryByStatus(
                order.getId(),
                order.getDataPedido(),
                order.getStatus(),
                RestaurantMapper.toRestaurantSummaryResponse(order.getRestaurante())
        );
    }

    public static OrderFilterSummaryResponse toOrderFilterSummaryResponse(Order order) {
        return new OrderFilterSummaryResponse(
                order.getId(),
                order.getValorTotal(),
                RestaurantMapper.toRestaurantSummaryResponse(order.getRestaurante()),
                UserMapper.toClientSummaryResponse(order.getCliente()),
                order.getStatus()
        );
    }
}
