package com.admincore.microservice.product.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductNotFoundExceptionTest {
    @Test
    void shouldStoreMessage() {
        String expectedMessage = "Product with ID 1 not found";
        ProductNotFoundException exception = new ProductNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
