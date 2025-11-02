package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.Cozinha;
import com.vitoriadeveloper.vifood.domain.ports.in.ICozinhaPort;
import com.vitoriadeveloper.vifood.infra.repositories.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CozinhaService implements ICozinhaPort {
    private final CozinhaRepository repository;

    @Override
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Cozinha> listarCozinhaPorId(Long id) {
        return repository.findById(id);
    }
}
