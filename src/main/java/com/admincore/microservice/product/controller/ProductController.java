package com.admincore.microservice.product.controller;

import com.admincore.microservice.product.dto.JsonApiResponse;
import com.admincore.microservice.product.dto.ProductRequest;
import com.admincore.microservice.product.dto.ProductResponse;
import com.admincore.microservice.product.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Crea un producto con nombre, precio y una descripción opcional. Retorna el producto creado en formato JSON:API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(mediaType = "application/vnd.api+json",
                            schema = @Schema(implementation = JsonApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Verifique los datos enviados.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json", produces = "application/vnd.api+json")
    public ResponseEntity<JsonApiResponse<ProductResponse>> create(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new JsonApiResponse<>(productServiceImpl.create(request)));
    }

    @Operation(
            summary = "Obtener un producto por ID",
            description = "Devuelve la información detallada de un producto específico identificado por su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/vnd.api+json",
                            schema = @Schema(implementation = JsonApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content)
    })
    @GetMapping(value = "/{id}", produces = "application/vnd.api+json")
    public ResponseEntity<JsonApiResponse<ProductResponse>> get(
            @Parameter(description = "ID del producto a consultar") @PathVariable Long id) {
        return ResponseEntity.ok(new JsonApiResponse<>(productServiceImpl.getById(id)));
    }

    @Operation(
            summary = "Listar todos los productos",
            description = "Devuelve una lista de todos los productos disponibles en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
                    content = @Content(mediaType = "application/vnd.api+json",
                            schema = @Schema(implementation = JsonApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content)
    })
    @GetMapping(produces = "application/vnd.api+json")
    public ResponseEntity<JsonApiResponse<List<ProductResponse>>> getAll() {
        return ResponseEntity.ok(new JsonApiResponse<>(productServiceImpl.getAll()));
    }
}