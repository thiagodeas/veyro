package com.veyro.dto;

import com.veyro.enums.Method;
import tools.jackson.databind.JsonNode;

public record RouteResponseDTO(Long id, Method method, String path, Integer status, JsonNode body) {}
