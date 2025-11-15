package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantRepositoryPort {
    private final RestaurantRepository jpaRepository;

    @Override
    public List<Restaurant> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant body) {
        return jpaRepository.save(body);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

}
