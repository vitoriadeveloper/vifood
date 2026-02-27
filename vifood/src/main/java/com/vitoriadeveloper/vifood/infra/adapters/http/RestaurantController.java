package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.ProductService;
import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.ProductCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.ProductUpdateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.RestaurantUpdateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.ProductResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.ProductMapper;
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
    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse create(@Valid @RequestBody RestaurantCreateRequest body) {
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
    public RestaurantResponse updateById(@PathVariable UUID id, @Valid @RequestBody RestaurantUpdateRequest body) {
        var restaurant = service.findById(id);

        RestaurantMapper.merge(body, restaurant);
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

    @PostMapping("/{idRestaurante}/formas-pagamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associatePaymentMethod(@PathVariable UUID idRestaurante, @RequestParam UUID idFormaPagamento) {
        service.associatePaymentMethod(idRestaurante, idFormaPagamento);
    }

    @DeleteMapping("/{idRestaurante}/formas-pagamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociatePaymentMethod(@PathVariable UUID idRestaurante, @RequestParam UUID idFormaPagamento) {
        service.disassociatePaymentMethod(idRestaurante, idFormaPagamento);
    }

    @GetMapping("/{restauranteId}/produtos")
    public List<ProductResponse> findProductsByRestaurantId(@PathVariable UUID restauranteId) {
        var products = productService.findByRestaurantId(restauranteId);
        return ProductMapper.toCollectionResponse(products);
    }

    @GetMapping("/{restauranteId}/produtos/{produtoId}")
    public ProductResponse findProductByRestaurantId(@PathVariable UUID restauranteId, @PathVariable UUID produtoId) {
        var products = productService.findByIdAndRestaurantId(produtoId, restauranteId);
        return ProductMapper.toResponse(products);
    }

    @PostMapping("/{restauranteId}/produtos")
    public ProductResponse addProductToRestaurant(@Valid @RequestBody ProductCreateRequest product, @PathVariable UUID restauranteId) {
        var products = ProductMapper.toDomain(product);
        var savedProduct = productService.create(restauranteId, products);

        return ProductMapper.toResponse(savedProduct);
    }

    @PutMapping("/{restauranteId}/produtos/{produtoId}")
    public ProductResponse updateProduct(@Valid @RequestBody ProductUpdateRequest body, @PathVariable UUID produtoId, @PathVariable UUID restauranteId) {
        var product = productService.findById(produtoId);

        ProductMapper.merge(body, product);

        var productSaved = productService.update(restauranteId, produtoId, product);

        return ProductMapper.toResponse(productSaved);
    }

    @DeleteMapping("/{restauranteId}/produtos/{produtoId}")
    public void removingProductToRestaurant(@PathVariable UUID produtoId, @PathVariable UUID restauranteId) {
        productService.delete(produtoId, restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    public void openRestaurant(@PathVariable UUID restauranteId) {
        service.openRestaurant(restauranteId);
    }


    @PutMapping("/{restauranteId}/fechamento")
    public void closeRestaurant(@PathVariable UUID restauranteId) {
        service.closeRestaurant(restauranteId);
    }

}
