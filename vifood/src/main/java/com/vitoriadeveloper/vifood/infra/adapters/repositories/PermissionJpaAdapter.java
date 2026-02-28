package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.UserPermission;
import com.vitoriadeveloper.vifood.domain.ports.out.IPermissionRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class PermissionJpaAdapter implements IPermissionRepositoryPort {
    private final UserPermissionRepository jpaRepository;

    @Override
    public List<UserPermission> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<UserPermission> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public UserPermission save(UserPermission userPermission) {
        return jpaRepository.save(userPermission);
    }
}
