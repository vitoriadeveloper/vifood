package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.KitchenService;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.KitchenResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.KitchenResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class KitchenController {
    private final KitchenService service;

    @PostMapping
    public KitchenResponse create(@RequestBody @Validated Kitchen body) {
        Kitchen kitchen = service.create(body);
        return KitchenResponseMapper.toResponse(kitchen);
    }

    @GetMapping
    public List<KitchenResponse> findAll() {
        return KitchenResponseMapper.toCollectionResponse(service.findAll());
    }

    @GetMapping({"/{id}"})
    public KitchenResponse findById(@PathVariable UUID id) {
        var kitchen =  service.findById(id);
        return KitchenResponseMapper.toResponse(kitchen);
    }

    @PutMapping("/{id}")
    public KitchenResponse updateById(@PathVariable UUID id, @RequestBody Kitchen body) {
        var kitchen = service.updateById(id, body);
        return KitchenResponseMapper.toResponse(kitchen);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
