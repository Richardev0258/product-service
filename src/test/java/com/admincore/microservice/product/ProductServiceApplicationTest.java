package com.admincore.microservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
public class ProductServiceApplicationTest {
    @Test
    void contextLoads() {
        // Test de verificación solo invoca el main
        ProductServiceApplication.main(new String[]{});
    }
}
