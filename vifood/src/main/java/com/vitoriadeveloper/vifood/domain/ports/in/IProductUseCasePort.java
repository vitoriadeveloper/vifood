package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.ProductNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface IProductUseCasePort {
    Product findById(UUID id) throws ProductNotFoundException;
    Product findByIdAndRestaurantId(UUID productId, UUID restaurantId) throws RestaurantNotFoundException, ProductNotFoundException;
    List<Product> findByRestaurantId(UUID restaurantId) throws RestaurantNotFoundException;
    Product create(UUID restaurantId,Product product);
    void delete(UUID productId, UUID restaurantId) throws RestaurantNotFoundException, ProductNotFoundException;
    Product update(UUID restaurantId, UUID productId, Product product) throws ProductNotFoundException, RestaurantNotFoundException;
}
