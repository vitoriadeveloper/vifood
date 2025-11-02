package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;

public interface ICozinhaPort {
    List<Cozinha> listar();
    Optional<Cozinha> listarCozinhaPorId(Long id);
}
