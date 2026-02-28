package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.PermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.UserPermission;

import java.util.List;
import java.util.UUID;

public interface IPermissionUseCasePort {

    UserPermission findById(UUID id) throws PermissionNotFoundException;

    List<UserPermission> findAll();

    void delete(UUID id) throws PermissionNotFoundException;

    void updateById(UUID id, UserPermission userPermission) throws PermissionNotFoundException;

    UserPermission save(UserPermission userPermission);
}
