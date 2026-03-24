package com.vitoriadeveloper.vifood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DailySales {
    private LocalDate data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}
