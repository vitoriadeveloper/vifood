package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.StatesService;
import com.vitoriadeveloper.vifood.domain.model.State;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class StateController {
    private final StatesService service;

    @GetMapping
    public List<State> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public State findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
