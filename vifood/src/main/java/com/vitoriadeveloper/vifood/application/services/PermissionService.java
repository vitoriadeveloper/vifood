package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.PermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.UserPermission;
import com.vitoriadeveloper.vifood.domain.ports.in.IPermissionUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IPermissionRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;


@Validated
@Service
@AllArgsConstructor
public class PermissionService implements IPermissionUseCasePort {
    private final IPermissionRepositoryPort repository;

    @Override
    public UserPermission findById(UUID id) throws PermissionNotFoundException {
        return repository.findById(id).orElseThrow(() -> new PermissionNotFoundException(id));
    }

    @Override
    public List<UserPermission> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) throws PermissionNotFoundException {
        repository.findById(id).orElseThrow(() -> new PermissionNotFoundException(id));
        repository.delete(id);
    }

    @Override
    public void updateById(UUID id, UserPermission request) throws PermissionNotFoundException {
        var permission = repository.findById(id).orElseThrow(() -> new PermissionNotFoundException(id));

        if (request.getNome() != null) {
            permission.setNome(request.getNome());
        }
        if (request.getDescricao() != null) {
            permission.setDescricao(request.getDescricao());
        }

        repository.save(permission);
    }

    @Override
    public UserPermission save(UserPermission userPermission) {
        return repository.save(userPermission);
    }
}
