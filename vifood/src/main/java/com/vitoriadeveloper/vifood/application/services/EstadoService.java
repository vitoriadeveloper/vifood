package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.Estado;
import com.vitoriadeveloper.vifood.domain.ports.in.IEstadoPort;
import com.vitoriadeveloper.vifood.infra.repositories.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService implements IEstadoPort {
    private final EstadoRepository repository;

    @Override
    public List<Estado> listar() {
        return repository.findAll();
    }
}
