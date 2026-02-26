package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.exceptions.PaymentMethodNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.RestaurantRepository;
import com.vitoriadeveloper.vifood.infra.repositories.spec.RestaurantSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantRepositoryPort {
    private final RestaurantRepository jpaRepository;
    private final PaymentMethodJpaAdapter paymentMethodJpaAdapter;

    @Override
    public List<Restaurant> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant body) {
        return jpaRepository.save(body);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> findByFilter(RestaurantFilter filter) {
        var spec = new RestaurantSpecificationBuilder(filter);
        return jpaRepository.findAll(spec);
    }

    @Override
    public void activate(UUID id) {
        Restaurant restaurant = jpaRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setAtivo(true);
        jpaRepository.save(restaurant);
    }

    @Override
    public void inactivate(UUID id) {
        Restaurant restaurant = jpaRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurant.setAtivo(false);
        jpaRepository.save(restaurant);
    }

    @Override
    public Restaurant associatePaymentMethod(UUID restaurantId, UUID paymentMethodId) {
        Restaurant restaurant = jpaRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        var paymentMethod = paymentMethodJpaAdapter.findById(paymentMethodId);

        if (!restaurant.getFormasPagamento().contains(paymentMethod)) {
            restaurant.getFormasPagamento().add(paymentMethod);
        }
        return jpaRepository.save(restaurant);
    }


    @Override
    public Restaurant disassociatePaymentMethod(UUID restaurantId, UUID paymentMethodId) {
        Restaurant restaurant = jpaRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        var paymentMethod = paymentMethodJpaAdapter.findById(paymentMethodId);

        restaurant.getFormasPagamento().remove(paymentMethod);
        return jpaRepository.save(restaurant);
    }

}
