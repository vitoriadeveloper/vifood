package com.vitoriadeveloper.vifood.domain.exceptions;

public class InvalidStateReferenceException extends RuntimeException {

    public InvalidStateReferenceException(Long stateId) {
        super("Estado de id: " + stateId + " não encontrado!");
    }
}
