package com.admincore.microservice.product.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void testAllArgsConstructor() {
        Product product = new Product(1L, "Test Product", 99.99, "Test description");

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(99.99, product.getPrice());
        assertEquals("Test description", product.getDescription());
    }
}
