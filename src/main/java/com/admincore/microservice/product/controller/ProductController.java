package com.admincore.microservice.product.controller;

import com.admincore.microservice.product.dto.JsonApiResponse;
import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.dto.ProductResponse;
import com.admincore.microservice.product.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @PostMapping
    public ResponseEntity<JsonApiResponse<ProductResponse>> create(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new JsonApiResponse<>(productServiceImpl.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonApiResponse<ProductResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(new JsonApiResponse<>(productServiceImpl.getById(id)));
    }

    @GetMapping
    public ResponseEntity<JsonApiResponse<List<ProductResponse>>> getAll() {
        return ResponseEntity.ok(new JsonApiResponse<>(productServiceImpl.getAll()));
    }
}
