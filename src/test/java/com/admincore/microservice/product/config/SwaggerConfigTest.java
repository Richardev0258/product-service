package com.admincore.microservice.product.config;

import com.admincore.microservice.product.config.SwaggerConfig;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwaggerConfigTest {
    @Test
    void testOpenAPIBean() {
        SwaggerConfig config = new SwaggerConfig();
        OpenAPI openAPI = config.productOpenAPI();
        assertNotNull(openAPI);
    }
}
