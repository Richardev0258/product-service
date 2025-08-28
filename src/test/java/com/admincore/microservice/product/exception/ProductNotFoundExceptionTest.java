package com.admincore.microservice.product.exception;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
public class ProductNotFoundExceptionTest {
    @Test
    void shouldStoreMessage() {
        String expectedMessage = "Product with ID 1 not found";
        ProductNotFoundException exception = new ProductNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
