package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.UserService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.UserCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.UserResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponse> findAll() {
        var user = service.findAll();
        return user.stream().map(UserMapper::toResponse).toList();
    }

    @PostMapping
    public UserResponse create(@Validated @RequestBody UserCreateRequest request) {
        var user = UserMapper.toDomain(request);
        var userSaved = service.save(user);
        return UserMapper.toResponse(userSaved);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable UUID id) {
        var user = service.findById(id);
        return UserMapper.toResponse(user);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
