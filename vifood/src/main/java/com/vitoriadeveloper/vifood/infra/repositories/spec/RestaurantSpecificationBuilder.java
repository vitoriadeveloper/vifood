package com.vitoriadeveloper.vifood.infra.repositories.spec;

import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RestaurantSpecificationBuilder implements Specification<Restaurant> {
    private final RestaurantFilter filter;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getNome() != null && !filter.getNome().isEmpty()) {
            predicates.add(
                    builder.like(builder.lower(root.get("nome")),
                            "%" + filter.getNome().toLowerCase() + "%"
                    )
            );
        }

        if (filter.getTaxaFreteMin() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("taxaFrete"), filter.getTaxaFreteMin())
            );
        }

        if (filter.getTaxaFreteMax() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("taxaFrete"), filter.getTaxaFreteMax())
            );
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
