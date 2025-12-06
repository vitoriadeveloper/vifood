package com.vitoriadeveloper.vifood.domain.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantFilter {
    private String nome;
    private Double taxaFreteMin;
    private Double taxaFreteMax;
}
