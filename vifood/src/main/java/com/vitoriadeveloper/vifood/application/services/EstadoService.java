package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.Estado;
import com.vitoriadeveloper.vifood.domain.ports.in.IEstadoUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IEstadoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService implements IEstadoUseCasePort {
    private final IEstadoRepositoryPort repository;

    @Override
    public List<Estado> listar() {
       return repository.findAll();
    }
}
