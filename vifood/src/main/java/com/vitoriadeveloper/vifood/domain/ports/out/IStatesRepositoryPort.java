package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.State;

import java.util.List;
import java.util.Optional;

public interface IStatesRepositoryPort {
    List<State> findAll();
    Optional<State> findById(Long id);
    void deleteById(Long id);
}
