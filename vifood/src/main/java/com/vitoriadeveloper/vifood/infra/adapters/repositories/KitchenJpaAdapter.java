package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KitchenJpaAdapter implements IKitchenRepositoryPort {
    private final KitchenRepository jpaRepository;

    @Override
    public List<Kitchen> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Kitchen> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Kitchen save(Kitchen body) {
        return jpaRepository.save(body);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

}
