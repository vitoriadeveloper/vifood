package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.Estado;

import java.util.List;

public interface IEstadoRepositoryPort {
    List<Estado> findAll();
}
