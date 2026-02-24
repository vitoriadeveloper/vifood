package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.UserNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.User;
import com.vitoriadeveloper.vifood.domain.ports.in.IUserUseCasePort;
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

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) throws UserNotFoundException {
        repository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
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
}
