package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.User;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.UserCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.ClientSummaryResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.UserResponse;

import java.util.List;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getDataCadastro(),
                GroupPermissionMapper.toResponseList(user.getGrupos())
        );
    }


    public static User toDomain(UserCreateRequest request) {
        User user = new User();
        user.setNome(request.nome());
        user.setSenha(request.senha());
        user.setEmail(request.email());
        return user;
    }

    public static List<UserResponse> toCollectionList(List<User> users) {
        return users.stream().map(UserMapper::toResponse).toList();
    }

    public static ClientSummaryResponse toClientSummaryResponse(User user) {
        return new ClientSummaryResponse(
                user.getId(),
                user.getNome()
        );
    }
}
