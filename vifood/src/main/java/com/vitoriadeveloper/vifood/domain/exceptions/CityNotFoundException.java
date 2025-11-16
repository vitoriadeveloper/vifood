package com.vitoriadeveloper.vifood.domain.exceptions;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Long id) {
        super("Cidade de id: " + id + " não encontrado!");
    }
}
