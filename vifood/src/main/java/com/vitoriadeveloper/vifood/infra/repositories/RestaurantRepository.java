package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, UUID>, JpaSpecificationExecutor<Restaurant> {
}
