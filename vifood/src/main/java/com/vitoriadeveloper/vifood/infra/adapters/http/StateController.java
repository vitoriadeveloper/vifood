package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.StatesService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.StateResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.StateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class StateController {
    private final StatesService service;

    @GetMapping
    public List<StateResponse> findAll() {
        var states = service.findAll();
        return StateMapper.toResponseList(states);
    }

    @GetMapping("/{id}")
    public StateResponse findById(@PathVariable UUID id) {
        var state = service.findById(id);
        return StateMapper.toResponse(state);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
