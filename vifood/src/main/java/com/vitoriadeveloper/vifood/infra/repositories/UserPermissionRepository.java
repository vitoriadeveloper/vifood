package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPermissionRepository extends CustomJpaRepository<PaymentMethod, UUID> {
}
