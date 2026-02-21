package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.GroupPermission;
import com.vitoriadeveloper.vifood.domain.ports.in.IGroupPermissionUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IGroupPermissionRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class GroupPermissionService implements IGroupPermissionUseCasePort {
    private final IGroupPermissionRepositoryPort repository;


    @Override
    public GroupPermission findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new GroupPermissionNotFoundException(id));
    }

    @Override
    public List<GroupPermission> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) throws GroupPermissionNotFoundException {
        repository.findById(id).orElseThrow(() -> new GroupPermissionNotFoundException(id));
        repository.delete(id);
    }

    @Override
    public void updateById(UUID id, GroupPermission groupPermission) throws GroupPermissionNotFoundException {
        repository.findById(id).orElseThrow(() -> new GroupPermissionNotFoundException(id));
        repository.updateById(id, groupPermission);
    }

    @Override
    public GroupPermission save(GroupPermission groupPermission) {
        return repository.save(groupPermission);
    }
}
