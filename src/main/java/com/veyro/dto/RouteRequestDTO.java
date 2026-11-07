package com.veyro.dto;

import com.veyro.enums.Method;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.JsonNode;

public record RouteRequestDTO(@NotNull Method method, @NotBlank String path, int status, JsonNode body) {}
