package com.admincore.microservice.product.service;

import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.dto.ProductResponse;
import com.admincore.microservice.product.exception.ProductNotFoundException;
import com.admincore.microservice.product.model.Product;
import com.admincore.microservice.product.repository.ProductRepository;
import com.admincore.microservice.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Laptop");
        request.setPrice(1200.0);
        request.setDescription("High-end");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Laptop");
        savedProduct.setPrice(1200.0);
        savedProduct.setDescription("High-end");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductResponse response = productService.create(request);

        assertNotNull(response);
        assertEquals("Laptop", response.getName());
        assertEquals(1200.0, response.getPrice());
        assertEquals("High-end", response.getDescription());

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());
        assertEquals("Laptop", productCaptor.getValue().getName());
    }

    @Test
    void testGetProductById_Success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setPrice(800.0);
        product.setDescription("Latest model");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse response = productService.getById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Phone", response.getName());
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getById(999L));
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Phone");
        product1.setPrice(800.0);
        product1.setDescription("Latest model");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Tablet");
        product2.setPrice(600.0);
        product2.setDescription("10-inch screen");

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<ProductResponse> responses = productService.getAll();

        assertEquals(2, responses.size());
        assertEquals("Phone", responses.get(0).getName());
        assertEquals("Tablet", responses.get(1).getName());
    }
}
