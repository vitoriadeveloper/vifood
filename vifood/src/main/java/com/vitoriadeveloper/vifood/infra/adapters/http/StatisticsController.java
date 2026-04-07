package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.StatisticsService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.StatisticsResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.StatisticsMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StatisticsResponse> getDailyStatistics(
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @RequestParam(required = false) UUID restauranteId
    ) {
        var toRequestDomain = StatisticsMapper.toDomain(restauranteId, dataInicio, dataFim);
        var result = statisticsService.getDailySales(toRequestDomain);
        return StatisticsMapper.toResponseList(result);
    }

    @GetMapping(path = "/vendas-diarias/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getDailyStatisticsByPdf(
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @RequestParam(required = false) UUID restauranteId
    ) {
        var filter = StatisticsMapper.toDomain(restauranteId, dataInicio, dataFim);
        byte[] pdf = statisticsService.getDailySalesByPdf(filter);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio.pdf")
                .body(pdf);
    }
}
