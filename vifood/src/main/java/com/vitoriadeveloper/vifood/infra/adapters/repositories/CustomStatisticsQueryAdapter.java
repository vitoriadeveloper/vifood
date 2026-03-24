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
import java.time.LocalTime;
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
    // TODO - verificar porque não esta filtrando por data passada no padrao 2026-03-23 nem no formato 23/03/2026
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
        if (filters.getDataInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataPedido"),
                    filters.getDataInicio().atStartOfDay().atOffset(root.get("dataPedido").getJavaType() == null
                            ? ZoneOffset.UTC : OffsetDateTime.now().getOffset())));
        }

        if (filters.getDataFim() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(
                            root.get("dataPedido"),
                            filters.getDataFim().atTime(LocalTime.MAX).atOffset(OffsetDateTime.now().getOffset())
                    )
            );
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
