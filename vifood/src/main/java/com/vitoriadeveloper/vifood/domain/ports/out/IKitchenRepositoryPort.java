package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IKitchenRepositoryPort {
    List<Kitchen> findAll();
    Optional<Kitchen> findById(Long id);
    Kitchen save(Kitchen body);
    void deleteById(Long id);
}
