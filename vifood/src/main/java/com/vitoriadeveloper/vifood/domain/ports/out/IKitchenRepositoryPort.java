package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IKitchenRepositoryPort {
    List<Kitchen> findAll();
    Optional<Kitchen> findById(UUID id);
    Kitchen save(Kitchen body);
    void deleteById(UUID id);
}
