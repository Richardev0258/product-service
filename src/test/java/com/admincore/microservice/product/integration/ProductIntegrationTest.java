package com.admincore.microservice.product.integration;

import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.model.Product;
import com.admincore.microservice.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanDb() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateProductSuccessfully() throws Exception {
        ProductRequest request = new ProductRequest("Test", 800.00, "Test Description");

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.attributes.name").value("Test"));

        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void shouldFetchProductById() throws Exception {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(999.00);
        product.setDescription("High-end");

        Product saved = repository.save(product);

        mockMvc.perform(get("/api/products/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.attributes.name").value("Laptop"));
    }

    @Test
    void shouldReturn404ForMissingProduct() throws Exception {
        mockMvc.perform(get("/api/products/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldListAllProducts() throws Exception {
        Product p1 = new Product();
        p1.setName("One");
        p1.setPrice(880.00);
        p1.setDescription("Desc1");

        Product p2 = new Product();
        p2.setName("Two");
        p2.setPrice(200.00);
        p2.setDescription("Desc2");

        repository.save(p1);
        repository.save(p2);
        repository.flush();

        System.out.println("Total en BD: " + repository.findAll().size());
        System.out.println("Productos guardados en base de datos: " + repository.findAll().size());
        repository.findAll().forEach(p -> System.out.println(p.getId() + " - " + p.getName()));

        mockMvc.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.attributes.length()").value(2));
    }
}
