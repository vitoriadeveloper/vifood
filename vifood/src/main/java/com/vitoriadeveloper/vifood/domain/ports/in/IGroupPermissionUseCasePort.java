package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.GroupPermission;

import java.util.List;
import java.util.UUID;

public interface IGroupPermissionUseCasePort {

    GroupPermission findById(UUID id) throws GroupPermissionNotFoundException;

    List<GroupPermission> findAll();

    void delete(UUID id) throws GroupPermissionNotFoundException;

    void updateById(UUID id, GroupPermission groupPermission) throws GroupPermissionNotFoundException;

    GroupPermission save(GroupPermission groupPermission);
}
