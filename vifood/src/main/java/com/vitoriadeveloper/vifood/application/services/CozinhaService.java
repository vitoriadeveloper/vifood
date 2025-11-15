package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.CozinhaNaoEncontradaException;
import com.vitoriadeveloper.vifood.domain.model.Cozinha;
import com.vitoriadeveloper.vifood.domain.ports.in.ICozinhaUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.ICozinhaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CozinhaService implements ICozinhaUseCasePort {
    private final ICozinhaRepositoryPort repository;

    @Override
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Cozinha> listarCozinhaPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cozinha atualizar(Long id, Cozinha body) {
        Cozinha cozinha = repository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(id));

        BeanUtils.copyProperties(body, cozinha, "id");

        return repository.save(cozinha);
    }


    @Override
    public void deletar(Long id) {
        Cozinha cozinhaEncontrada = repository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(id));

        repository.delete(id);
    }

}
