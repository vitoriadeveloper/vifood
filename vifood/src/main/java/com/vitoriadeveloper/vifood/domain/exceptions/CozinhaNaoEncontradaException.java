package com.vitoriadeveloper.vifood.domain.exceptions;

public class CozinhaNaoEncontradaException extends RuntimeException {

    public CozinhaNaoEncontradaException(Long id) {
        super("Cozinha não encontrada: " + id);
    }
}
