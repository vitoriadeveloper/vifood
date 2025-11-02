package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.EstadoService;
import com.vitoriadeveloper.vifood.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService service;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        List<Estado> resultado = service.listar();
        return ResponseEntity.ok().body(resultado);
    }
}
