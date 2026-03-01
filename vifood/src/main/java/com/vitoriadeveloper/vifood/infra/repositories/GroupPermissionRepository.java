package com.vitoriadeveloper.vifood.infra.repositories;

import com.vitoriadeveloper.vifood.domain.model.GroupPermission;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupPermissionRepository extends CustomJpaRepository<GroupPermission, UUID> {
    Optional<GroupPermission> findByIdAndPermissoesId(UUID grupoId, UUID permissaoId);
}
