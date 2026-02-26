package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.ProductNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IRestaurantUseCasePort {
    Restaurant create(Restaurant body);
    List<Restaurant> findAll();
    Restaurant findById(UUID id) throws RestaurantNotFoundException;
    Restaurant updateById(UUID id, Restaurant body) throws RestaurantNotFoundException;
    void deleteById(UUID id);
    void updatePartial(UUID id, Map<String, Object> fields);
    List<Restaurant> findByFilter(RestaurantFilter filter);
    void activate(UUID id);
    void inactivate(UUID id);
    void associatePaymentMethod(UUID restaurantId, UUID paymentMethodId);
    void disassociatePaymentMethod(UUID restaurantId, UUID paymentMethodId);
    List<Product> findProductsByRestaurantId(UUID restaurantId) throws  RestaurantNotFoundException;
    Product findProductById(UUID restaurantId, UUID productId) throws RestaurantNotFoundException, ProductNotFoundException;
    Product createProduct(UUID restaurantId, Product body) throws RestaurantNotFoundException, ProductNotFoundException;
    Product updateProductById(UUID restaurantId, UUID productId, Product body) throws RestaurantNotFoundException, ProductNotFoundException;
    void deleteProductById(UUID restaurantId, UUID productId) throws RestaurantNotFoundException, ProductNotFoundException;
}
