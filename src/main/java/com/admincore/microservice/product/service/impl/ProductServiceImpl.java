package com.admincore.microservice.product.service.impl;
import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.dto.ProductResponse;
import com.admincore.microservice.product.exception.ProductNotFoundException;
import com.admincore.microservice.product.model.Product;
import com.admincore.microservice.product.repository.ProductRepository;
import com.admincore.microservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        Product saved = repository.save(product);
        return toResponse(saved);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        return toResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private ProductResponse toResponse(Product p) {
        ProductResponse resp = new ProductResponse();
        resp.setId(p.getId());
        resp.setName(p.getName());
        resp.setPrice(p.getPrice());
        resp.setDescription(p.getDescription());
        return resp;
    }
}
