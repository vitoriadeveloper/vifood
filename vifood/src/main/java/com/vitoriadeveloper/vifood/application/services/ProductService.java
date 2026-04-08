package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.BusinessException;
import com.vitoriadeveloper.vifood.domain.exceptions.ProductNotFoundException;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Product;
import com.vitoriadeveloper.vifood.domain.model.ProductImage;
import com.vitoriadeveloper.vifood.domain.ports.in.IProductUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IProductRepositoryPort;
import com.vitoriadeveloper.vifood.domain.ports.out.IRestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@AllArgsConstructor
public class ProductService implements IProductUseCasePort {
    private final IProductRepositoryPort repository;
    private final IRestaurantRepositoryPort restaurantRepository;


    @Override
    public Product findById(UUID id) throws ProductNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product findByIdAndRestaurantId(UUID productId, UUID restaurantId) throws RestaurantNotFoundException, ProductNotFoundException {
        var restaurantExists = restaurantRepository.findById(restaurantId);
        if (restaurantExists.isEmpty()) {
            throw new RestaurantNotFoundException(restaurantId);
        }
        return repository.findByIdAndRestaurantId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
    }

    @Override
    public List<Product> findByRestaurantId(UUID restaurantId) throws RestaurantNotFoundException {
        restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        return repository.findByRestaurantId(restaurantId);
    }

    @Transactional
    @Override
    public Product create(UUID restaurantId, Product product) {
        var restaurantExists = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        product.setRestaurante(restaurantExists);
        return repository.save(product);
    }

    @Transactional
    @Override
    public void delete(UUID productId, UUID restaurantId) throws RestaurantNotFoundException, ProductNotFoundException {
        Product product = repository.findByIdAndRestaurantId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
        repository.delete(product);
    }

    @Transactional
    @Override
    public Product update(UUID restaurantId, UUID productId, Product body) throws ProductNotFoundException, RestaurantNotFoundException {
        Product product = repository.findByIdAndRestaurantId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
        product.setNome(body.getNome());
        product.setPreco(body.getPreco());
        product.setDescricao(body.getDescricao());
        product.setAtivo(body.isAtivo());

        return repository.save(product);
    }

    @Transactional
    public void addOrUpdateProductImage(UUID restaurantId, UUID productId, ProductImage request, String originalFilename) throws ProductNotFoundException, RestaurantNotFoundException {
        Product product = repository.findByIdAndRestaurantId(productId, restaurantId).orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));

        String imageId = generateImageId(originalFilename);

        if (product.getFotoProduto() != null) {
            ProductImage existing = product.getFotoProduto();
            existing.setDescription(request.getDescription());
            existing.setImageId(imageId);
        } else {
            request.setImageId(imageId);
            request.setProduct(product);
            product.setFotoProduto(request);
        }

        repository.save(product);

    }

    private String generateImageId(String originalFilename) {
        String extension = getExtension(originalFilename);
        return "produtos/" + UUID.randomUUID() + extension;
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.'));
    }

    private void validateImage(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null ||
                (!contentType.equals("image/jpeg") &&
                        !contentType.equals("image/png"))) {

            throw new BusinessException("Tipo de arquivo inválido");
        }
    }
}
