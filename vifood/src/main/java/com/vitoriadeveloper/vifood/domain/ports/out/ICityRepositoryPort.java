package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.City;
import java.util.List;
import java.util.Optional;

public interface ICityRepositoryPort {
    List<City> findAll();
    Optional<City> findById(Long id);
    void delete(Long id);
}
