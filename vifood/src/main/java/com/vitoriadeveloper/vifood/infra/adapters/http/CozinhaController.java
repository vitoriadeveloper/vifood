package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.CozinhaService;
import com.vitoriadeveloper.vifood.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {
    private final CozinhaService service;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> resultado = service.listar();
        return ResponseEntity.ok().body(resultado);
    }

}
