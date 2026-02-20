package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.State;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStateRepositoryPort {
    List<State> findAll();
    Optional<State> findById(UUID id);
    void delete(UUID id);
}
