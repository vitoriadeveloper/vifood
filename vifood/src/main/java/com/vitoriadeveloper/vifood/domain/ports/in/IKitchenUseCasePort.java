package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;

import java.util.List;
import java.util.Optional;

public interface IKitchenUseCasePort {
    List<Kitchen> findAll();
    Optional<Kitchen> findById(Long id);
    Kitchen updateById(Long id, Kitchen body);
    void deleteById(Long id);
}
