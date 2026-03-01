package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.PermissionService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.CreatePermissionRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.PermissionResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.PermissionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vifood/permissoes")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService service;

    @GetMapping
    public List<PermissionResponse> findAllPermissionsOfGroup() {
        var permissions = service.findAll();

        return PermissionMapper.toResponseList(permissions);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removingPermission(
            @PathVariable UUID permissaoId
    ) {
        service.delete(permissaoId);
    }

    @PostMapping
    public PermissionResponse createPermission(@Valid @RequestBody CreatePermissionRequest request) {
        var permission = PermissionMapper.toDomain(request);

        var saved = service.save(permission);

        return PermissionMapper.toResponse(saved);
    }

}
