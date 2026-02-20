package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.RestaurantResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse create(@Valid @RequestBody RestaurantRequest body) {
        var restaurant = RestaurantResponseMapper.toDomain(body);
        var saved = service.create(restaurant);
        return RestaurantResponseMapper.toResponse(saved);
    }

    @GetMapping
    public List<RestaurantResponse> findAll(RestaurantFilter filters) {
        if (filters.getTaxaFreteMax() == null && filters.getTaxaFreteMin() == null && filters.getNome() == null) {
            var restaurants = service.findAll();
            return restaurants.stream().map(RestaurantResponseMapper::toResponse).toList();
        }
        var restaurants = service.findByFilter(filters);
        return restaurants.stream().map(RestaurantResponseMapper::toResponse).toList();
    }

    @GetMapping({"/{id}"})
    public RestaurantResponse findById(@PathVariable Long id) {
        var restaurant = service.findById(id);
        return RestaurantResponseMapper.toResponse(restaurant);
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateById(@PathVariable Long id, @Valid @RequestBody RestaurantRequest body) {
        var restaurant = RestaurantResponseMapper.toDomain(body);
        var updated = service.updateById(id, restaurant);
        return RestaurantResponseMapper.toResponse(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updatePartial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> fields) {
        service.updatePartial(id, fields);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
