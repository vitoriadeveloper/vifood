package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.State;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateRepository extends CustomJpaRepository<State, UUID> {
}
