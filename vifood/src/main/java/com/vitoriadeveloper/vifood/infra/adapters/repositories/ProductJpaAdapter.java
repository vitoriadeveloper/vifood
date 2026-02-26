package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.exceptions.ProductNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.domain.ports.out.IProductRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.ProductRepository;
import com.vitoriadeveloper.vifood.infra.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductRepositoryPort {
    private final ProductRepository repository;
    private final RestaurantRepository restaurantRepository;


    @Override
    public Optional<Product> findByIdAndRestaurantId(UUID productId, UUID restaurantId) {
        return repository.findByIdAndRestauranteId(productId, restaurantId);
    }

    @Override
    public List<Product> findByRestaurantId(UUID restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantNotFoundException(restaurantId);
        }
        return repository.findByRestauranteId(restaurantId);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(UUID productId, UUID restaurantId) {
        Product product = repository.findByIdAndRestauranteId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));

        repository.delete(product);
    }

}
