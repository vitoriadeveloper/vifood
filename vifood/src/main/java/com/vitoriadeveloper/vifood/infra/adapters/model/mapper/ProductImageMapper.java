package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.ProductImage;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.request.ProductImageUpdateOrCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    public ProductImage toDomain(ProductImageUpdateOrCreateRequest request) {
        var image = new ProductImage();
        image.setDescription(request.descricao());
        return image;
    }
}
