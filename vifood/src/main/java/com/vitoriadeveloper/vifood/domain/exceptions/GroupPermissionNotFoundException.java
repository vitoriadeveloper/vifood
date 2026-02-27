package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class GroupPermissionNotFoundException extends BusinessException {

    public GroupPermissionNotFoundException(UUID id) {
        super("Grupo de permissão de id: " + id + " não encontrado!");
    }
}
