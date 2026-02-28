package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.UserPermission;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.CreatePermissionRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.PermissionResponse;

import java.util.List;

public class PermissionMapper {

    public static UserPermission toDomain(CreatePermissionRequest request) {
        UserPermission userPermission = new UserPermission();
        userPermission.setNome(request.nome());
        userPermission.setDescricao(request.descricao());

        return userPermission;
    }

    public static PermissionResponse toResponse(UserPermission userPermission) {
        return new PermissionResponse(userPermission.getNome(), userPermission.getId(), userPermission.getDescricao());
    }

    public static List<PermissionResponse> toResponseList(List<UserPermission> userPermission) {
        return userPermission.stream().map(PermissionMapper::toResponse).toList();
    }
}
