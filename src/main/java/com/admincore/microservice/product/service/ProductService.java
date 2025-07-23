package com.admincore.microservice.product.service;

import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
}
