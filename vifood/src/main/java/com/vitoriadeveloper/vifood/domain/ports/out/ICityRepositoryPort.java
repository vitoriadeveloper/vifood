package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.City;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICityRepositoryPort {
    List<City> findAll();
    Optional<City> findById(UUID id);
    void delete(UUID id);
    void save(City city);
}
