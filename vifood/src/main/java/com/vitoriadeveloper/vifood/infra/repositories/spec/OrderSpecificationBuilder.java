package com.vitoriadeveloper.vifood.infra.repositories.spec;

import com.vitoriadeveloper.vifood.domain.filters.OrderFilter;
import com.vitoriadeveloper.vifood.domain.model.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OrderSpecificationBuilder implements Specification<Order> {
    private final OrderFilter filter;

    @Nullable
    @Override
    public Predicate toPredicate(Root<Order> root, @Nullable CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getClienteId() != null) {
            predicates.add(builder.equal(root.get("cliente").get("id"), filter.getClienteId()));
        }

        if(filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filter.getRestauranteId()));
        }

        if(filter.getStatus() != null) {
            predicates.add(builder.equal(root.get("status"), filter.getStatus()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
