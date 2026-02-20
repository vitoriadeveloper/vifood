package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.City;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends CustomJpaRepository<City, UUID> {
}
