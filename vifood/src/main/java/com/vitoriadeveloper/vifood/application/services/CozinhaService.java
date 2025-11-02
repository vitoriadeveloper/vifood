package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.Cozinha;
import com.vitoriadeveloper.vifood.domain.ports.in.ICozinhaPort;
import com.vitoriadeveloper.vifood.infra.repositories.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CozinhaService implements ICozinhaPort {
    private final CozinhaRepository cozinhaRepository;

    @Override
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }
}
