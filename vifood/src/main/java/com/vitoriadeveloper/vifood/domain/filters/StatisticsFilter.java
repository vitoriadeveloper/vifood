package com.vitoriadeveloper.vifood.domain.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsFilter {
    UUID restauranteId;
    LocalDate dataInicio;
    LocalDate dataFim;
}
