package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.domain.model.ProductImage;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.ProductImageUpdateOrCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    public static ProductImage toDomain(Product product, ProductImageUpdateOrCreateRequest request) {
        var image = new ProductImage();
        image.setProduct(product);
        image.setDescription(request.descricao());
        return image;
    }


    public void updateEntity(ProductImage image, ProductImageUpdateOrCreateRequest request) {
        image.setDescription(request.descricao());
    }
}
