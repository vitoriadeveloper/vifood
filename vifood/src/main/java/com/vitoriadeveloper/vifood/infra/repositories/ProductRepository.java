package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, UUID>{
    Optional<Product> findByIdAndRestauranteId(UUID productId, UUID restauranteId);
    List<Product> findByRestauranteId(UUID restauranteId);
}
