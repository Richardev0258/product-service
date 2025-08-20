package com.admincore.microservice.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI productOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description("API para la gestión de productos. Permite crear, obtener y listar productos.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Tu Nombre o Equipo")
                                .url("https://tu-web.com")
                                .email("tu-email@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8081/api").description("Servidor de Desarrollo")
                ));
    }
}
