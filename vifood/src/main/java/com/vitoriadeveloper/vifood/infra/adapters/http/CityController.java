package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.CityService;
import com.vitoriadeveloper.vifood.domain.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping
    public List<City> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable UUID id, @RequestBody City city) {
        service.updateById(id, city);
    }
}
