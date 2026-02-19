package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.ports.in.IKitchenUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class KitchenService implements IKitchenUseCasePort {
    private final IKitchenRepositoryPort repository;

    @Transactional
    @Override
    public Kitchen create(Kitchen body) {
        return repository.save(body);
    }

    @Override
    public List<Kitchen> findAll() {
        return repository.findAll();
    }

    @Override
    public Kitchen findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new KitchenNotFoundException(id));
    }

    @Transactional
    @Override
    public Kitchen updateById(Long id, Kitchen body) {
        Kitchen kitchen = repository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));

        BeanUtils.copyProperties(body, kitchen, "id");

        return repository.save(kitchen);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            repository.findById(id)
                    .orElseThrow(() -> new KitchenNotFoundException(id));

            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}
