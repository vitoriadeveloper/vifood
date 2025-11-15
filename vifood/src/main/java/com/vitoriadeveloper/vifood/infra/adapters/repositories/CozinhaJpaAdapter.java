package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.Cozinha;
import com.vitoriadeveloper.vifood.domain.ports.out.ICozinhaRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CozinhaJpaAdapter implements ICozinhaRepositoryPort {
    private final CozinhaRepository jpaRepository;

    @Override
    public List<Cozinha> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Cozinha> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Cozinha save(Cozinha body) {
        return jpaRepository.save(body);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

}
