package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.domain.model.PaginationRequest;

import java.util.UUID;

public interface IKitchenUseCasePort {
    Kitchen create(Kitchen body);
    Pagination<Kitchen> findAll(PaginationRequest paginationRequest);
    Kitchen findById(UUID id);
    Kitchen updateById(UUID id, Kitchen body);
    void deleteById(UUID id);
}
