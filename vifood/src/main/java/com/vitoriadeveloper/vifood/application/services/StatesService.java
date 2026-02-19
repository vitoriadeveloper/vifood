package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.StateNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.State;
import com.vitoriadeveloper.vifood.domain.ports.in.IStateUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IStateRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


import java.util.List;

@Validated
@Service
@AllArgsConstructor
public class StatesService implements IStateUseCasePort {
    private final IStateRepositoryPort repository;

    @Override
    public List<State> findAll() {
        return repository.findAll();
    }

    @Override
    public State findById(Long id)  {
        return repository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    @Override
    @Transactional
    public void delete(Long id) throws StateNotFoundException {
        repository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
        repository.delete(id);
    }

}
