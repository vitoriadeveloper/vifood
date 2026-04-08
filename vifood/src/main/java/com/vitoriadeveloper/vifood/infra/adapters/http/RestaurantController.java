package com.vitoriadeveloper.vifood.infra.adapters.http;


import com.vitoriadeveloper.vifood.application.services.ProductService;
import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.filters.RestaurantFilter;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.*;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.ProductResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.UserResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.ProductImageMapper;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.ProductMapper;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.RestaurantMapper;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.UserMapper;
import com.vitoriadeveloper.vifood.infra.utils.ImageValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;
    private final ProductService productService;
    private final ProductImageMapper productImageMapper;
    private final ImageValidator imageValidator;

    // # APENAS RESTAURANTES #
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
        service.activateRestaurants(id);
    }

    @PutMapping("/{id}/inativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable UUID id) {
        service.inactivateRestaurants(id);
    }

    // # FORMAS DE PAGAMENTO DE RESTAURANTES #
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

    // # PRODUTOS DE UM RESTAURANTES #
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

    @PutMapping(value = "/{restauranteId}/produtos/{produtoId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addOrUpdateImageUpload(
            @PathVariable UUID restauranteId,
            @PathVariable UUID produtoId,
            @Valid @ModelAttribute ProductImageUpdateOrCreateRequest body

    ) {
        imageValidator.validate(body.arquivo());
        var domain = productImageMapper.toDomain(body);
        String originalFilename = Optional.ofNullable(body.arquivo().getOriginalFilename())
                .orElse("image");
        productService.addOrUpdateProductImage(restauranteId, produtoId, domain, originalFilename);

    }

    // # ABERTURA E FECHAMENTO DE RESTAURANT #
    @PutMapping("/{restauranteId}/abertura")
    public void openRestaurant(@PathVariable UUID restauranteId) {
        service.openRestaurant(restauranteId);
    }


    @PutMapping("/{restauranteId}/fechamento")
    public void closeRestaurant(@PathVariable UUID restauranteId) {
        service.closeRestaurant(restauranteId);
    }

    @PutMapping("/{restauranteId}/responsaveis/{responsavelId}")
    public void associateRestaurantOwner(@PathVariable UUID restauranteId, @PathVariable UUID responsavelId) {
        service.associateRestaurantOwner(restauranteId, responsavelId);
    }

    // # RESPONSAVEIS RESTAURANTES #
    @DeleteMapping("/{restauranteId}/responsaveis/{responsavelId}")
    public void disassociateRestaurantOwner(@PathVariable UUID restauranteId, @PathVariable UUID responsavelId) {
        service.disassociateRestaurantOwner(restauranteId, responsavelId);
    }

    @GetMapping("/{restauranteId}/responsaveis")
    public List<UserResponse> findRestaurantOwners(@PathVariable UUID restauranteId) {

        var owners = service.findRestaurantOwners(restauranteId);
        return UserMapper.toCollectionList(owners);
    }

    // # ACOES EM LOTE PARA RESTAURANTES #
    @PutMapping("/ativacao-em-lote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchActivate(@RequestBody RestaurantBatchRequest request) {
        service.activateBatch(request.restauranteIds());
    }

    @PutMapping("/inativacao-em-lote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchInactivate(@RequestBody RestaurantBatchRequest request) {
        service.inactivateBatch(request.restauranteIds());
    }


}
