package com.vitoriadeveloper.vifood.domain.filters;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderFilter {
    UUID clienteId;
    UUID restauranteId;
    String status;
}
