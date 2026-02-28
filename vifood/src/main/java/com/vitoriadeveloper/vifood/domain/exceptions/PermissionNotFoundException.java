package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class PermissionNotFoundException extends BusinessException {

    public PermissionNotFoundException(UUID id) {
        super("Permissão de permissão de id: " + id + " não encontrado!");
    }
}
