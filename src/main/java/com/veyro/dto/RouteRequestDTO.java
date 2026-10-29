package com.veyro.dto;

import com.veyro.enums.Method;
import tools.jackson.databind.JsonNode;

public record RouteRequestDTO(Method method, String path, int status, JsonNode body) {}
