package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.Estado;

import java.util.List;

public interface IEstadoUseCasePort {
    List<Estado> listar();
}
