package com.admincore.microservice.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    @Schema(description = "ID único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Laptop Gamer")
    private String name;

    @Schema(description = "Precio del producto", example = "1200.00")
    private double price;

    @Schema(description = "Descripción del producto", example = "Potente laptop para juegos y trabajo.")
    private String description;
}
