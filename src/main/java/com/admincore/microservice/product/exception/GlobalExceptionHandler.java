package com.admincore.microservice.product.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ProductNotFoundException ex) {
        Map<String, Object> error = Map.of(
                "errors", List.of(Map.of(
                        "status", "404",
                        "title", "Product Not Found",
                        "detail", ex.getMessage()
                ))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ProductUnexpectedException.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(ProductUnexpectedException ex) {
        return handleGeneric(ex, null);
    }

    public ResponseEntity<Map<String, Object>> handleGeneric(ProductUnexpectedException ex, HttpServletRequest request) {
        String path = (request != null) ? request.getRequestURI() : "";
        if (path.startsWith("/actuator")) {
            throw new ProductUnexpectedException(ex.getMessage(), ex);
        }
        Map<String, Object> error = Map.of(
                "errors", List.of(Map.of(
                        "status", "500",
                        "title", "Internal Server Error",
                        "detail", ex.getMessage()
                ))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}