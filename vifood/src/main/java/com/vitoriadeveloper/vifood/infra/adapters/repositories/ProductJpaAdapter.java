package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.domain.ports.out.IProductRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductRepositoryPort {
    private final ProductRepository repository;


    @Override
    public Optional<Product> findByIdAndRestaurantId(UUID productId, UUID restaurantId) {
        return repository.findByIdAndRestauranteId(productId, restaurantId);
    }

    @Override
    public List<Product> findByRestaurantId(UUID restaurantId) {
        return repository.findByRestauranteId(restaurantId);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

}
