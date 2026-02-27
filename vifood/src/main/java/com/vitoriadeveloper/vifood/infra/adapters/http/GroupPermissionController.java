package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.GroupPermissionService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.GroupCreatePermissionRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.GroupPermissionResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.GroupPermissionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vifood/grupos-permissao")
@RequiredArgsConstructor
public class GroupPermissionController {
    private final GroupPermissionService service;

    @GetMapping
    public List<GroupPermissionResponse> findAll() {
        var roles = service.findAll();
        return GroupPermissionMapper.toResponseList(roles);
    }

    @GetMapping("/{id}")
    public GroupPermissionResponse findById(@PathVariable UUID id) {
        var role = service.findById(id);
        return GroupPermissionMapper.toResponse(role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable UUID id, @Valid @RequestBody GroupCreatePermissionRequest request) {
        var role = GroupPermissionMapper.toDomain(request);
        service.updateById(id, role);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupPermissionResponse create(@Valid @RequestBody GroupCreatePermissionRequest request) {
        var role = GroupPermissionMapper.toDomain(request);
        var createdRole = service.save(role);
        return GroupPermissionMapper.toResponse(createdRole);
    }
}
