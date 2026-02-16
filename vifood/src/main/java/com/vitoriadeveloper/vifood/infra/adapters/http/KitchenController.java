package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.KitchenService;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class KitchenController {
    private final KitchenService service;

    @PostMapping
    public Kitchen create(@RequestBody Kitchen body) {
        return service.create(body);
    }

    @GetMapping
    public List<Kitchen> findAll() {
        return  service.findAll();
    }

    @GetMapping({"/{id}"})
    public Kitchen findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Kitchen updateById(@PathVariable Long id, @RequestBody Kitchen body) {
        return service.updateById(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
