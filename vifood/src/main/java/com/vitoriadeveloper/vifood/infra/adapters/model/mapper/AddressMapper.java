package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.Address;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.AddressCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.AddressResponse;

public class AddressMapper {

    public static AddressResponse toResponse(Address address) {
        return new AddressResponse(address.getCep(), address.getLogradouro(), address.getNumero(), address.getComplemento(), address.getBairro(), CityMapper.toResponse(address.getCidade()));
    }

    public static Address toDomain(AddressCreateRequest request) {
        Address address = new Address();
        address.setCep(request.cep());
        address.setLogradouro(request.logradouro());
        address.setNumero(request.numero());
        address.setComplemento(request.complemento());
        address.setBairro(request.bairro());
        if (request.cidadeId() != null) {
            address.setCidade(CityMapper.toDomain(request.cidadeId()));
        }
        return address;
    }
}
