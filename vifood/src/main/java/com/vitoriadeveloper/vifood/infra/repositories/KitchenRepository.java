package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, UUID> {
}
