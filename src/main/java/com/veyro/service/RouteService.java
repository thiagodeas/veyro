package com.veyro.service;

import com.veyro.dto.RouteRequestDTO;
import com.veyro.dto.RouteResponseDTO;
import com.veyro.exception.InvalidStatusCodeException;
import com.veyro.exception.RouteAlreadyExistsException;
import com.veyro.model.Route;
import com.veyro.repository.RouteRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Locale;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final ObjectMapper mapper;

    public RouteService(RouteRepository routeRepository, ObjectMapper mapper) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }

    public RouteResponseDTO createRoute(RouteRequestDTO dto, String projectIdentifier) {

        String path = dto.path().trim().toLowerCase(Locale.ROOT);
        String formattedPath = path.startsWith("/") ? path : "/" + path;

        formattedPath = formattedPath.replaceAll("/{2,}", "/");

        if (formattedPath.endsWith("/") && formattedPath.length() > 1) {
            formattedPath = formattedPath.substring(0, formattedPath.length() - 1);
        }

        if(dto.status() < 100 || dto.status() > 599) {
            throw new InvalidStatusCodeException();
        }

        String body = dto.body() != null ? dto.body().toString() : null;

        Optional<Route> existingRoute = routeRepository.findByMethodAndPathAndProjectIdentifier(dto.method(), formattedPath, projectIdentifier);

        if (existingRoute.isPresent()) {
            throw new RouteAlreadyExistsException();
        }

        Route newRoute = routeRepository.save(
                new Route(
                        dto.method(),
                        formattedPath,
                        dto.status(),
                        body,
                        projectIdentifier
                ));

        JsonNode jsonString = newRoute.getBody() != null ? mapper.readTree(newRoute.getBody()) : null;

        return new RouteResponseDTO(
                newRoute.getId(),
                newRoute.getMethod(),
                newRoute.getPath(),
                newRoute.getStatus(),
                jsonString
        );
    }
}
