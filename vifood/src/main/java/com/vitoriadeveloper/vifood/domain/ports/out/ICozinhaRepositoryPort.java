package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;

public interface ICozinhaRepositoryPort {
    List<Cozinha> findAll();
    Optional<Cozinha> findById(Long id);
}
