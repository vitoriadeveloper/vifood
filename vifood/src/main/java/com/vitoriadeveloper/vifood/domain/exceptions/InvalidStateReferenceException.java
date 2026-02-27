package com.vitoriadeveloper.vifood.domain.exceptions;

import java.util.UUID;

public class InvalidStateReferenceException extends BusinessException {

    public InvalidStateReferenceException(UUID stateId) {
        super("Estado de id: " + stateId + " não encontrado!");
    }
}
