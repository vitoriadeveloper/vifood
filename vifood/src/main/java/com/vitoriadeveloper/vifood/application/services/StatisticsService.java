package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;
import com.vitoriadeveloper.vifood.domain.ports.in.IStatisticsUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IStatisticsRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService implements IStatisticsUseCasePort {
    private final IStatisticsRepositoryPort repository;
    private final PdfService pdfService;

    @Override
    public List<DailySales> getDailySales(StatisticsFilter filters) throws RestaurantNotFoundException {
        return repository.getDailySalesByRestaurantId(filters);
    }

    @Override
    public byte[] getDailySalesByPdf(StatisticsFilter filters) {
        List<DailySales> dailySales = repository.getDailySalesByRestaurantId(filters);

        return pdfService.generatePdf(dailySales);
    }


}
