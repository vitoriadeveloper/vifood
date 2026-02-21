package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.GroupPermission;

import java.util.List;
import java.util.UUID;

public interface IGroupPermissionRepositoryPort {
    List<GroupPermission> findAll();

    GroupPermission findById(UUID id);

    void delete(UUID id);

    void updateById(UUID id, GroupPermission groupPermission);

    void save(GroupPermission groupPermission);
}
