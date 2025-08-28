package com.admincore.microservice.product.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleNotFound_shouldReturn404WithErrorMessage() {
        String message = "Product with ID 1 not found";
        ProductNotFoundException ex = new ProductNotFoundException(message);

        ResponseEntity<Map<String, Object>> response = handler.handleNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("errors"));
        assertTrue(response.getBody().get("errors").toString().contains(message));
    }

    @Test
    void handleGeneric_shouldReturn500WithErrorMessage() {
        String message = "Unexpected error occurred";
        ProductUnexpectedException ex = new ProductUnexpectedException(message);

        ResponseEntity<Map<String, Object>> response = handler.handleGeneric(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("errors"));
        assertTrue(response.getBody().get("errors").toString().contains(message));
    }

    @Test
    void handleGeneric_withHttpServletRequest_shouldReturn500() {
        String message = "Another unexpected error";
        ProductUnexpectedException ex = new ProductUnexpectedException(message);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/test");

        ResponseEntity<Map<String, Object>> response = handler.handleGeneric(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().get("errors").toString().contains(message));
    }

    @Test
    void handleGeneric_withActuatorPath_shouldThrowRuntimeException() {
        ProductUnexpectedException ex = new ProductUnexpectedException("Actuator error");

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/actuator/health");

        assertThrows(RuntimeException.class, () -> handler.handleGeneric(ex, request));
    }
}