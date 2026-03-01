package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.GroupPermission;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.GroupCreatePermissionRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.GroupPermissionResponse;

import java.util.List;

public class GroupPermissionMapper {

    public static GroupPermission toDomain(GroupCreatePermissionRequest request) {
        GroupPermission groupPermission = new GroupPermission();
        groupPermission.setNome(request.nome());

        return groupPermission;
    }

    public static GroupPermissionResponse toResponse(GroupPermission groupPermission) {
        return new GroupPermissionResponse(groupPermission.getNome(), groupPermission.getId(), PermissionMapper.toResponseList(groupPermission.getPermissoes()));
    }

    public static List<GroupPermissionResponse> toResponseList(List<GroupPermission> groupPermissions) {
        return groupPermissions.stream().map(GroupPermissionMapper::toResponse).toList();
    }
}
