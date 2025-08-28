package com.admincore.microservice.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
@Schema(description = "Representa un producto disponible en el catálogo")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del producto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Nombre del producto", example = "Laptop Gamer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @Schema(description = "Precio del producto", example = "1200.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private double price;

    @Schema(description = "Descripción opcional del producto", example = "Potente laptop para juegos y trabajo.")
    private String description;
}
