package com.admincore.microservice.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI productOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Product Service API").version("v1.0.0"));
    }
}
