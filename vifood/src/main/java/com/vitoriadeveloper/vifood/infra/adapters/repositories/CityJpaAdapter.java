package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.City;
import com.vitoriadeveloper.vifood.domain.ports.out.ICityRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CityJpaAdapter implements ICityRepositoryPort {
    private final CityRepository jpaRepository;

    @Override
    public List<City> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void save(City city) {
        jpaRepository.save(city);
    }
}
