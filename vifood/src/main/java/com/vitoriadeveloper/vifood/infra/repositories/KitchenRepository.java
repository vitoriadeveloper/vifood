package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {
}
