package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;


    @PostMapping
    public Restaurant create(@RequestBody Restaurant body) {
        return service.create(body);
    }

    @GetMapping
    public List<Restaurant> findAll(RestaurantFilter filters) {
        if (filters.getTaxaFreteMax() == null && filters.getTaxaFreteMin() == null && filters.getNome() == null) {
            return service.findAll();
        }

        return service.findByFilter(filters);
    }

    @GetMapping({"/{id}"})
    public Restaurant findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Restaurant updateById(@PathVariable Long id, @RequestBody Restaurant body) {
        return service.updateById(id, body);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        service.updatePartial(id, fields);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
