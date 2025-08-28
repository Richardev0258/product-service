package com.admincore.microservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
public class ServletInitializerTest {
    @Test
    void testConfigureDoesNotThrow() {
        ServletInitializer initializer = new ServletInitializer();
        SpringApplicationBuilder builder = new SpringApplicationBuilder();

        assertDoesNotThrow(() -> initializer.configure(builder));
    }
}
