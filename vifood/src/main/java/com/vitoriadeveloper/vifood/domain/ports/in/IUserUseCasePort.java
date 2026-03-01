package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.GroupPermissionNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.UserNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserUseCasePort {
    List<User> findAll();
    User save(User user);
    User findById(UUID id) throws UserNotFoundException;
    void delete(UUID id) throws UserNotFoundException;

    void associateUserToAGroup(UUID userId, UUID groupId) throws UserNotFoundException, GroupPermissionNotFoundException;
    void disassociateUserToAGroup(UUID userId, UUID groupId) throws UserNotFoundException, GroupPermissionNotFoundException;
}
