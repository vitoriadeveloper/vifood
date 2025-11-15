package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.StateService;
import com.vitoriadeveloper.vifood.domain.model.State;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
@RequiredArgsConstructor
public class StateController {
    private final StateService service;

    @GetMapping
    public ResponseEntity<List<State>> listar() {
        List<State> resultado = service.findAll();
        return ResponseEntity.ok(resultado);
    }
}
