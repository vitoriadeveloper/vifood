package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.RestaurantMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse create(@Valid @RequestBody RestaurantRequest body) {
        var restaurant = RestaurantMapper.toDomain(body);
        var saved = service.create(restaurant);
        return RestaurantMapper.toResponse(saved);
    }

    @GetMapping
    public List<RestaurantResponse> findAll(RestaurantFilter filters) {
        if (filters.getTaxaFreteMax() == null && filters.getTaxaFreteMin() == null && filters.getNome() == null) {
            var restaurants = service.findAll();
            return restaurants.stream().map(RestaurantMapper::toResponse).toList();
        }
        var restaurants = service.findByFilter(filters);
        return restaurants.stream().map(RestaurantMapper::toResponse).toList();
    }

    @GetMapping({"/{id}"})
    public RestaurantResponse findById(@PathVariable UUID id) {
        var restaurant = service.findById(id);
        return RestaurantMapper.toResponse(restaurant);
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateById(@PathVariable UUID id, @Valid @RequestBody RestaurantRequest body) {
        var restaurant = RestaurantMapper.toDomain(body);
        var updated = service.updateById(id, restaurant);
        return RestaurantMapper.toResponse(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updatePartial(@PathVariable UUID id, @Valid @RequestBody Map<String, Object> fields) {
        service.updatePartial(id, fields);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable UUID id) {
        service.activate(id);
    }

    @PutMapping("/{id}/inativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable UUID id) {
        service.inactivate(id);
    }
}
