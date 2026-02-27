package com.vitoriadeveloper.vifood.application.services;
import com.vitoriadeveloper.vifood.domain.exceptions.ProductNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.domain.ports.in.IProductUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IProductRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class ProductService implements IProductUseCasePort {
    private final IProductRepositoryPort repository;
    private final IRestaurantRepositoryPort restaurantRepository;


    @Override
    public Product findByIdAndRestaurantId(UUID productId, UUID restaurantId) throws RestaurantNotFoundException, ProductNotFoundException {
        var restaurantExists = restaurantRepository.findById(restaurantId);
        if (restaurantExists.isEmpty()) {
            throw new RestaurantNotFoundException(restaurantId);
        }
        return repository.findByIdAndRestaurantId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
    }

    @Override
    public List<Product> findByRestaurantId(UUID restaurantId) throws RestaurantNotFoundException {
        restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        return repository.findByRestaurantId(restaurantId);
    }

    @Transactional
    @Override
    public Product create(UUID restaurantId,Product product) {
        var restaurantExists = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        product.setRestaurante(restaurantExists);
        return repository.save(product);
    }

    @Transactional
    @Override
    public void delete(UUID productId, UUID restaurantId) throws RestaurantNotFoundException, ProductNotFoundException {
        Product product = repository.findByIdAndRestaurantId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
        repository.delete(product);
    }

    @Transactional
    @Override
    public Product update(UUID restaurantId, UUID productId,Product body) throws ProductNotFoundException, RestaurantNotFoundException {
        Product product = repository.findByIdAndRestaurantId(productId,restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
        product.setNome(body.getNome());
        product.setPreco(body.getPreco());
        product.setDescricao(body.getDescricao());
        product.setAtivo(body.isAtivo());

        return repository.save(product);
    }

}
