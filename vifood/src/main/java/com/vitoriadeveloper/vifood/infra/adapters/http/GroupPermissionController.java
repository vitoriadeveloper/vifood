package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.GroupPermissionService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.GroupCreatePermissionRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.GroupPermissionResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.PermissionResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.GroupPermissionMapper;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.PermissionMapper;
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

    // ===================== GRUPOS =====================
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

    // ===================== PERMISSÕES DO GRUPO =====================

    @GetMapping("/{grupoId}/permissoes")
    public List<PermissionResponse> findAllPermissionsOfGroup(
            @PathVariable UUID grupoId
    ) {
        var group = service.findById(grupoId);

        return PermissionMapper.toResponseList(group.getPermissoes());
    }

    @PutMapping("/{grupoId}/permissoes/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associatePermission(
            @PathVariable UUID grupoId,
            @PathVariable UUID permissaoId
    ) {
        service.associateGroupWithPermission(grupoId, permissaoId);
    }

    @DeleteMapping("/{grupoId}/permissoes/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociatePermission(
            @PathVariable UUID grupoId,
            @PathVariable UUID permissaoId
    ) {
        service.disassociateGroupWithPermission(grupoId, permissaoId);
    }

}
