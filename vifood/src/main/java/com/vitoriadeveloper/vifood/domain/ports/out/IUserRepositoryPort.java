package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(UUID id);
    User save(User user);
    void delete(UUID id);
}
