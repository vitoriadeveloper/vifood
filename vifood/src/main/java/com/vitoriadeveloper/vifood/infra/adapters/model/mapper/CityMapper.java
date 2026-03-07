package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.City;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.CityResponse;

import java.util.List;
import java.util.UUID;

public class CityMapper {

    public static CityResponse toResponse(City city) {
        if(city == null) return null;

        return new CityResponse(
                city.getId(),
                city.getNome(),
                city.getEstado() != null ? StateMapper.toResponse(city.getEstado()) : null
        );
    }

    public static List<CityResponse> toResponseList(List<City> cities) {
        return cities.stream().map(CityMapper::toResponse).toList();
    }

    public static City toDomain(UUID cityId) {
        City city = new City();
        city.setId(cityId);
        return city;
    }
}
