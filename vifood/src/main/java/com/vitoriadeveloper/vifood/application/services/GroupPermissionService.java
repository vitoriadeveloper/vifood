package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.PermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.GroupPermission;
import com.vitoriadeveloper.vifood.domain.ports.in.IGroupPermissionUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IGroupPermissionRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IPermissionRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class GroupPermissionService implements IGroupPermissionUseCasePort {
    private final IGroupPermissionRepositoryPort repository;
    private final IPermissionRepositoryPort permissionRepository;


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
        GroupPermission group = repository.findById(id).orElseThrow(() -> new GroupPermissionNotFoundException(id));

        if (groupPermission.getNome() != null) {
            group.setNome(groupPermission.getNome());
        }
        repository.save(group);
    }

    @Override
    public GroupPermission save(GroupPermission groupPermission) {
        return repository.save(groupPermission);
    }

    @Override
    public GroupPermission findPermissionByGroupId(UUID groupId, UUID permissionId) throws GroupPermissionNotFoundException, PermissionNotFoundException {
        repository.findById(groupId).orElseThrow(() -> new GroupPermissionNotFoundException(groupId));
        return repository.findByIdAndPermissionId(groupId, permissionId).orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }

    @Override
    public void associateGroupWithPermission(UUID groupId, UUID permissionId) throws GroupPermissionNotFoundException, PermissionNotFoundException {
        var group = repository.findById(groupId).orElseThrow(() -> new GroupPermissionNotFoundException(groupId));

        var permission = permissionRepository.findById(permissionId).orElseThrow(() -> new PermissionNotFoundException(permissionId));

        group.addPermission(permission);

        repository.save(group);
    }

    @Override
    public void disassociateGroupWithPermission(UUID groupId, UUID permissionId) throws GroupPermissionNotFoundException, PermissionNotFoundException {
        var group = repository.findById(groupId).orElseThrow(() -> new GroupPermissionNotFoundException(groupId));

        var permission = permissionRepository.findById(permissionId).orElseThrow(() -> new PermissionNotFoundException(permissionId));

        group.removePermission(permission);

        repository.save(group);
    }

}
