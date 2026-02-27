package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.ProductCreateRequest;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.ProductResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.RestaurantSummaryResponse;

import java.util.List;

public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getNome(),
                product.getDescricao(),
                product.getPreco(),
                product.isAtivo(),
                product.getRestaurante() != null ? new RestaurantSummaryResponse(product.getRestaurante().getId(), product.getRestaurante().getNome()) : null
        );
    }

    public static Product toDomain(ProductCreateRequest request) {
        Product product = new Product();

        product.setNome(request.nome());
        product.setDescricao(request.descricao());
        product.setPreco(request.preco());

        return product;
    }

    public static List<ProductResponse> toCollectionResponse(List<Product> products) {
        return products
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }
}
