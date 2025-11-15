package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.KitchenService;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class KitchenController {
    private final KitchenService service;


    @PostMapping
    public ResponseEntity<Kitchen> create(@RequestBody Kitchen body) {
        Kitchen kitchen = service.create(body);
        return ResponseEntity.ok().body(kitchen);
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> findAll() {
        List<Kitchen> resultado = service.findAll();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Optional<Kitchen>> findById(@PathVariable Long id) {
        Optional<Kitchen> resultado = service.findById(id);
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> updateById(@PathVariable Long id, @RequestBody Kitchen body) {
        Kitchen kitchen = service.updateById(id, body);
        return ResponseEntity.ok().body(kitchen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
