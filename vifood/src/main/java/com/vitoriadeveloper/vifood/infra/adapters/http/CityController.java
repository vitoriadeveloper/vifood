package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.CityService;
import com.vitoriadeveloper.vifood.domain.model.City;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.CityResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.CityMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping
    public List<CityResponse> findAll() {
        var cities = service.findAll();
        return CityMapper.toResponseList(cities);
    }

    @GetMapping("/{id}")
    public CityResponse findById(@PathVariable UUID id) {
        var city = service.findById(id);
        return CityMapper.toResponse(city);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }


    @PutMapping("/{id}")
    public void updateById(@PathVariable UUID id, @Valid @RequestBody City city) {
        service.updateById(id, city);
    }
}
