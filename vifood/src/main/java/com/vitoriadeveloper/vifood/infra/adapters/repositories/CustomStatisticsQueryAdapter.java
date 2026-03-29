package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.filters.StatisticsFilter;
import com.vitoriadeveloper.vifood.domain.model.DailySales;
import com.vitoriadeveloper.vifood.domain.model.Order;
import com.vitoriadeveloper.vifood.infra.repositories.StatisticsQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomStatisticsQueryAdapter implements StatisticsQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySales> getDailySalesByRestaurantId(StatisticsFilter filters) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DailySales> query = builder.createQuery(DailySales.class);
        Root<Order> root = query.from(Order.class);

        Expression<LocalDate> dataExp = builder.function(
                "DATE",
                LocalDate.class,
                root.get("dataPedido")
        );
        Expression<Long> totalSales = builder.count(root.get("id"));
        Expression<BigDecimal> totalRevenue = builder.sum(root.get("valorTotal"));

        List<Predicate> predicates = new ArrayList<>();

        if (filters.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filters.getRestauranteId()));
        }

        ZoneOffset utcOffset = ZoneOffset.UTC;

        if (filters.getDataInicio() != null) {
            OffsetDateTime dataInicioWithTime = filters.getDataInicio().atStartOfDay().atOffset(utcOffset);
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataPedido"), dataInicioWithTime));
        }

        if (filters.getDataFim() != null) {
            OffsetDateTime dataFimWithTime = filters.getDataFim().plusDays(1).atStartOfDay().atOffset(utcOffset);
            predicates.add(builder.lessThan(root.get("dataPedido"), dataFimWithTime));
        }

        query.select(
                builder.construct(
                        DailySales.class,
                        dataExp,
                        totalSales,
                        totalRevenue
                )
        );

        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(dataExp);
        query.orderBy(builder.asc(dataExp));

        TypedQuery<DailySales> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}
