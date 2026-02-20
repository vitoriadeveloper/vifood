package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.StateNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.State;

import java.util.List;
import java.util.UUID;

public interface IStateUseCasePort {
    List<State> findAll();
    State findById(UUID id) throws StateNotFoundException;
    void delete(UUID id) throws StateNotFoundException;
}
