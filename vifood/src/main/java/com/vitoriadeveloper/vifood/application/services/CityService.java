package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.CityNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.InvalidStateReferenceException;
import com.vitoriadeveloper.vifood.domain.exceptions.StateNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.City;
import com.vitoriadeveloper.vifood.domain.model.State;
import com.vitoriadeveloper.vifood.domain.ports.in.ICityUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.ICityRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IStateRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class CityService implements ICityUseCasePort {
    private final ICityRepositoryPort repository;
    private final IStateRepositoryPort stateRepository;

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public City findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Override
    @Transactional
    public void delete(UUID id) throws CityNotFoundException {
        repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        repository.delete(id);
    }

    @Override
    @Transactional
    public void updateById(UUID id, City city) throws CityNotFoundException {
        City existingCity  = repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));

        State existingState  = stateRepository.findById(city.getEstado().getId())
                .orElseThrow(() -> new InvalidStateReferenceException(city.getEstado().getId()));

        existingCity.setNome(city.getNome());
        existingCity.setEstado(existingState);

        repository.save(existingCity);
    }
}
