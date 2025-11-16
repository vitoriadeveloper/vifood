package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;


    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant body) {
        Restaurant restaurant = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateById(@PathVariable Long id, @RequestBody Restaurant body) {
        Restaurant result = service.updateById(id, body);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        service.updatePartial(id, fields);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
