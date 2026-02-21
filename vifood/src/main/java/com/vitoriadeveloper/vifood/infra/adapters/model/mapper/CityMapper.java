package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.City;
import com.vitoriadeveloper.vifood.domain.model.State;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.CityResponse;

import java.util.List;

public class CityMapper {

    public static CityResponse toResponse(City city) {
        return new CityResponse(
                city.getId(),
                city.getNome(),
                StateMapper.toResponse(city.getEstado())
        );
    }

    public static List<CityResponse> toResponseList(List<City> cities) {
        return cities.stream().map(CityMapper::toResponse).toList();
    }

    public static City toDomain(CityResponse city) {
        State domainState = new State();
        domainState.setId(city.estado().id());
        domainState.setNome(city.estado().nome());
        domainState.setSigla(city.estado().sigla());

        City domainCity = new City();
        domainCity.setId(city.id());
        domainCity.setNome(city.nome());
        domainCity.setEstado(domainState);

        return domainCity;
    }
}
