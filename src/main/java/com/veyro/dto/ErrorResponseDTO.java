package com.veyro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ErrorResponseDTO(int status, String message, @JsonInclude(JsonInclude.Include.NON_NULL) Long existingRouteId) {

    public ErrorResponseDTO(int status, String message) {
        this(status, message, null);
    }

}
