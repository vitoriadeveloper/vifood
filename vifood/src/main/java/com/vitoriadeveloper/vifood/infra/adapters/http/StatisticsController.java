package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.StatisticsService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.StatisticsResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.StatisticsMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/vendas-diarias")
    public List<StatisticsResponse> consultarVendasDiarias(
            @RequestParam(required = false) LocalDate dataCriacaoInicio,
            @RequestParam(required = false) LocalDate dataCriacaoFim,
            @RequestParam(required = false) UUID restauranteId
    ) {
        var toRequestDomain = StatisticsMapper.toDomain(restauranteId, dataCriacaoInicio, dataCriacaoFim);
        var result = statisticsService.getDailySales(toRequestDomain);
        return StatisticsMapper.toResponseList(result);
    }
}
