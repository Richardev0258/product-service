package com.admincore.microservice.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "Estructura base de respuesta según el estándar JSON:API")
public class JsonApiResponse<T> {

    @Schema(description = "Datos de la respuesta")
    private JsonApiData<T> data;

    public JsonApiResponse(T attributes) {
        this.data = new JsonApiData<>(attributes);
    }

    @Getter
    @AllArgsConstructor
    @Schema(description = "Contenedor de datos de la respuesta JSON:API")
    static class JsonApiData<T> {
        @Schema(description = "Tipo del recurso", example = "resource", accessMode = Schema.AccessMode.READ_ONLY)
        private static final String TYPE = "resource";

        @Schema(description = "Atributos del recurso")
        private final T attributes;
    }

    public JsonApiData<T> getData() {
        return data;
    }
}
