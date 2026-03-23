package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/vendas-diarias")
    public Object consultarVendasDiarias(
            @RequestParam UUID restauranteId,
            @RequestParam String dataCriacaoInicio,
            @RequestParam String dataCriacaoFim
    ) {
        return statisticsService.getDailySales(restauranteId, dataCriacaoInicio, dataCriacaoFim);
    }
}
