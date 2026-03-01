package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.UserNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.User;
import com.vitoriadeveloper.vifood.domain.ports.in.IUserUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IGroupPermissionRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IUserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@AllArgsConstructor
@Service
public class UserService implements IUserUseCasePort {
    private final IUserRepositoryPort repository;
    private final IGroupPermissionRepositoryPort groupPermissionRepository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(UUID id) throws UserNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    @Override
    public void delete(UUID id) throws UserNotFoundException {
        repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        repository.delete(id);
    }

    @Override
    public void associateUserToAGroup(UUID userId, UUID groupId) throws UserNotFoundException, GroupPermissionNotFoundException {
        var user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        var group = groupPermissionRepository.findById(groupId).orElseThrow(() -> new GroupPermissionNotFoundException(groupId));

        user.addUserToGroupPermission(group);

        repository.save(user);
    }

    @Override
    public void disassociateUserToAGroup(UUID userId, UUID groupId) throws UserNotFoundException, GroupPermissionNotFoundException {
        var user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        var group = groupPermissionRepository.findById(groupId).orElseThrow(() -> new GroupPermissionNotFoundException(groupId));

        user.removeUserToGroupPermission(group);

        repository.save(user);
    }
}
