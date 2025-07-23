package com.admincore.microservice.product.controller;

import com.admincore.microservice.product.dto.JsonApiResponse;
import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.dto.ProductResponse;
import com.admincore.microservice.product.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Laptop");
        request.setDescription("High-end");
        request.setPrice(1500.0);

        ProductResponse response = new ProductResponse(1L, "Laptop", 1500.0,"High-end");

        Mockito.when(productServiceImpl.create(any(ProductRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.attributes.id").value(1L))
                .andExpect(jsonPath("$.data.attributes.name").value("Laptop"));
    }

    @Test
    void testGetProductById() throws Exception {
        ProductResponse response = new ProductResponse(1L, "Phone", 800.0,  "Latest model");

        Mockito.when(productServiceImpl.getById(eq(1L))).thenReturn(response);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.attributes.id").value(1L))
                .andExpect(jsonPath("$.data.attributes.name").value("Phone"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        List<ProductResponse> responseList = List.of(
                new ProductResponse(1L, "Phone", 800.0, "Latest model"),
                new ProductResponse(2L, "Tablet",600.0,  "10-inch screen")
        );

        Mockito.when(productServiceImpl.getAll()).thenReturn(responseList);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.attributes.length()").value(2))
                .andExpect(jsonPath("$.data.attributes[0].name").value("Phone"))
                .andExpect(jsonPath("$.data.attributes[1].name").value("Tablet"));
    }
}
