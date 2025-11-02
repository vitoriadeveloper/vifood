package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.Cozinha;

import java.util.List;

public interface ICozinhaPort {
    List<Cozinha> listar();
}
