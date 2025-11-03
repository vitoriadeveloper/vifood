package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.Estado;
import com.vitoriadeveloper.vifood.domain.ports.out.IEstadoRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EstadoJpaAdapter implements IEstadoRepositoryPort {
    private final EstadoRepository jpaRepository;

    @Override
    public List<Estado> findAll() {
        return jpaRepository.findAll();
    }
}
