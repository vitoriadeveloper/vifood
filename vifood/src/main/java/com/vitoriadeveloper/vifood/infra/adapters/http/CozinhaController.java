package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.CozinhaService;
import com.vitoriadeveloper.vifood.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CozinhaController {
    private final CozinhaService service;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> resultado = service.listar();
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Optional<Cozinha>> listarCozinhaPorId(@PathVariable Long id) {
        Optional<Cozinha> resultado = service.listarCozinhaPorId(id);
        return ResponseEntity.ok().body(resultado);
    }
}
