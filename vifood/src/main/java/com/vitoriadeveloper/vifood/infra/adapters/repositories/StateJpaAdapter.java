package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.State;
import com.vitoriadeveloper.vifood.domain.ports.out.IStateRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StateJpaAdapter implements IStateRepositoryPort {
    private final StateRepository jpaRepository;

    @Override
    public List<State> findAll() {
        return jpaRepository.findAll();
    }
}
