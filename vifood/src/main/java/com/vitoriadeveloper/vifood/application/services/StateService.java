package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.State;
import com.vitoriadeveloper.vifood.domain.ports.in.IStateUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IStateRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService implements IStateUseCasePort {
    private final IStateRepositoryPort repository;

    @Override
    public List<State> findAll() {
       return repository.findAll();
    }
}
