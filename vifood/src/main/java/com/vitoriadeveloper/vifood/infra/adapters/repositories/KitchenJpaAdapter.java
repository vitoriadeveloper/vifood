package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Pagination;
import com.vitoriadeveloper.vifood.domain.model.PaginationRequest;
import com.vitoriadeveloper.vifood.domain.ports.out.IKitchenRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KitchenJpaAdapter implements IKitchenRepositoryPort {
    private final KitchenRepository jpaRepository;

    @Override
    public Pagination<Kitchen> findAll(PaginationRequest paginationRequest) {
        var pageable = PageRequest.of(paginationRequest.page(), paginationRequest.size());
        var page = jpaRepository.findAll(pageable);
        return new Pagination<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements()
        );
    }

    @Override
    public Optional<Kitchen> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Kitchen save(Kitchen body) {
        return jpaRepository.save(body);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

}
