package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.PermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.GroupPermission;

import java.util.List;
import java.util.UUID;

public interface IGroupPermissionUseCasePort {

    GroupPermission findById(UUID id) throws GroupPermissionNotFoundException;

    List<GroupPermission> findAll();

    void delete(UUID id) throws GroupPermissionNotFoundException;

    void updateById(UUID id, GroupPermission groupPermission) throws GroupPermissionNotFoundException;

    GroupPermission save(GroupPermission groupPermission);

    GroupPermission findPermissionByGroupId(UUID groupId, UUID permissionId) throws GroupPermissionNotFoundException, PermissionNotFoundException;
    void associateGroupWithPermission(UUID groupId, UUID permissionId) throws GroupPermissionNotFoundException, PermissionNotFoundException;
    void disassociateGroupWithPermission(UUID groupId, UUID permissionId) throws GroupPermissionNotFoundException, PermissionNotFoundException;
}
