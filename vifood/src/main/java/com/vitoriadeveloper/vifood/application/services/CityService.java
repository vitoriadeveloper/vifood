package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.CityNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.City;
import com.vitoriadeveloper.vifood.domain.ports.in.ICityUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.ICityRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CityService implements ICityUseCasePort {
    private final ICityRepositoryPort repository;

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public City findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Override
    public void delete(Long id) throws CityNotFoundException {
        repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        repository.delete(id);
    }
}
