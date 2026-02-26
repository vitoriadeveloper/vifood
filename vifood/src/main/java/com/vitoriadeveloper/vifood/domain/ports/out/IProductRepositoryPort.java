package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductRepositoryPort {
    Optional<Product> findByIdAndRestaurantId(UUID productId, UUID restaurantId);

    List<Product> findByRestaurantId(UUID restaurantId);

    Product save(Product product);

    void delete(UUID productId,UUID restaurantId);
}
