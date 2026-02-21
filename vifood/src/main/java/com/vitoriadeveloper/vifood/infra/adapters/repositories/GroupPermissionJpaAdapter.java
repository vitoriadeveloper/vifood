package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.GroupPermission;
import com.vitoriadeveloper.vifood.domain.ports.out.IGroupPermissionRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.GroupPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GroupPermissionJpaAdapter implements IGroupPermissionRepositoryPort {
    private final GroupPermissionRepository jpaRepository;


    @Override
    public List<GroupPermission> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<GroupPermission> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void updateById(UUID id, GroupPermission groupPermission) {
        if (!jpaRepository.existsById(id)) {
            throw new GroupPermissionNotFoundException(id);
        }
        groupPermission.setId(id);
        jpaRepository.save(groupPermission);
    }

    @Override
    public GroupPermission save(GroupPermission groupPermission) {
        return jpaRepository.save(groupPermission);
    }
}
