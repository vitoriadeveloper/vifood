package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.User;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.UserRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.UserResponse;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getDataCadastro()
        );
    }


    public static User toDomain(UserRequest request) {
        User user = new User();
        user.setNome(request.nome());
        user.setSenha(request.senha());
        user.setEmail(request.email());
        return user;
    }
}
