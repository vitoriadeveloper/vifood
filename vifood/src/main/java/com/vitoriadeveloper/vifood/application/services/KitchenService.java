package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.ports.in.IKitchenUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenService implements IKitchenUseCasePort {
    private final IKitchenRepositoryPort repository;

    @Override
    public List<Kitchen> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Kitchen> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Kitchen updateById(Long id, Kitchen body) {
        Kitchen kitchen = repository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));

        BeanUtils.copyProperties(body, kitchen, "id");

        return repository.save(kitchen);
    }


    @Override
    public void deleteById(Long id) {
        Kitchen kitchenEncontrada = repository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));

        repository.deleteById(id);
    }

}
