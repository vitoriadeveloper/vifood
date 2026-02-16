package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.CityNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.City;

import java.util.List;

public interface ICityUseCasePort {
    List<City> findAll();
    City findById(Long id) throws CityNotFoundException;
    void delete(Long id) throws CityNotFoundException;
    void updateById(Long id, City city) throws CityNotFoundException;
}
