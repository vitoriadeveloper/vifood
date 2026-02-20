package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.CityNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.City;

import java.util.List;
import java.util.UUID;

public interface ICityUseCasePort {
    List<City> findAll();
    City findById(UUID id) throws CityNotFoundException;
    void delete(UUID id) throws CityNotFoundException;
    void updateById(UUID id, City city) throws CityNotFoundException;
}
