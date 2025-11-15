package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.State;

import java.util.List;

public interface IStateRepositoryPort {
    List<State> findAll();
}
