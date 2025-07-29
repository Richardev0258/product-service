package com.admincore.microservice.product.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
public class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleNotFound_shouldReturn404WithErrorMessage() {
        // Arrange
        String message = "Product with ID 1 not found";
        ProductNotFoundException ex = new ProductNotFoundException(message);

        // Act
        ResponseEntity<Map<String, Object>> response = handler.handleNotFound(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("errors"));
        assertTrue(response.getBody().get("errors").toString().contains(message));
    }

    @Test
    void handleGeneric_shouldReturn500WithErrorMessage() {
        // Arrange
        String message = "Unexpected error occurred";
        Exception ex = new Exception(message);

        // Act
        ResponseEntity<Map<String, Object>> response = handler.handleGeneric(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("errors"));
        assertTrue(response.getBody().get("errors").toString().contains(message));
    }
}
