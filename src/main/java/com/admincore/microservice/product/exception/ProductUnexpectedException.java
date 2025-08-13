package com.admincore.microservice.product.exception;

public class ProductUnexpectedException extends RuntimeException {
    public ProductUnexpectedException(String message) {
        super(message);
    }

    public ProductUnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}