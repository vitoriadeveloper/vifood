package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import java.util.List;
import java.util.UUID;

public interface IKitchenUseCasePort {
    Kitchen create(Kitchen body);
    List<Kitchen> findAll();
    Kitchen findById(UUID id);
    Kitchen updateById(UUID id, Kitchen body);
    void deleteById(UUID id);
}
