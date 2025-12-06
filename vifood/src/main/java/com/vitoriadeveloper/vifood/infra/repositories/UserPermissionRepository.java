package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends CustomJpaRepository<PaymentMethod, Long> {
}
