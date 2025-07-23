package com.admincore.microservice.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class JsonApiResponse<T> {
    private JsonApiData<T> data;

    public JsonApiResponse(T attributes) {
        this.data = new JsonApiData<>(attributes);
    }

    @Getter
    @AllArgsConstructor
    static class JsonApiData<T> {
        private final String type = "resource";
        private final T attributes;
    }

    public JsonApiData<T> getData() {
        return data;
    }
}
