package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.domain.model.PaginationRequest;

import java.util.Optional;
import java.util.UUID;

public interface IKitchenRepositoryPort {
    Pagination<Kitchen> findAll(PaginationRequest paginationRequest);
    Optional<Kitchen> findById(UUID id);
    Kitchen save(Kitchen body);
    void deleteById(UUID id);
}
