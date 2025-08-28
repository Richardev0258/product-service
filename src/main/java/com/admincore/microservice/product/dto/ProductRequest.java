package com.admincore.microservice.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @Schema(description = "Nombre del producto", example = "Laptop Gamer", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String name;

    @Schema(description = "Precio del producto", example = "1200.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private double price;

    @Schema(description = "Descripción opcional del producto", example = "Potente laptop para juegos y trabajo.")
    private String description;
}