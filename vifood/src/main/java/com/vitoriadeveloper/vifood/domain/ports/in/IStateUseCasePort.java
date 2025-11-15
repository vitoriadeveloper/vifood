package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.State;

import java.util.List;

public interface IStateUseCasePort {
    List<State> findAll();
}
