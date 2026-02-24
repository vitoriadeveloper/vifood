package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.UserNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserUseCasePort {
    List<User> findAll();
    User save(User user);
    User update(User user) throws UserNotFoundException;
    User findById(UUID id) throws UserNotFoundException;
    void delete(UUID id) throws UserNotFoundException;
}
