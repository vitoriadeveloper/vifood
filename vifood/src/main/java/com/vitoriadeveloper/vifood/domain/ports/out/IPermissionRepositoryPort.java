package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.UserPermission;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPermissionRepositoryPort {
    List<UserPermission> findAll();

    Optional<UserPermission> findById(UUID id);

    void delete(UUID id);

    UserPermission save(UserPermission userPermission);
}
