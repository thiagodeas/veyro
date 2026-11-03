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

        String path = dto.path().startsWith("/") ? dto.path().toLowerCase().trim() : "/" + dto.path().toLowerCase().trim();

        if(dto.status() < 100 || dto.status() > 599) {
            throw new InvalidStatusCodeException();
        }

        Optional<Route> existingRoute = routeRepository.findByMethodAndPathAndProjectIdentifier(dto.method(), path, projectIdentifier);

        if (existingRoute.isPresent()) {
            throw new RouteAlreadyExistsException();
        }

        Route newRoute = routeRepository.save(
                new Route(
                        dto.method(),
                        path,
                        dto.status(),
                        dto.body().toString(),
                        projectIdentifier
                ));

            JsonNode jsonString = mapper.readTree(newRoute.getBody());

        return new RouteResponseDTO(
                newRoute.getId(),
                newRoute.getMethod(),
                newRoute.getPath(),
                newRoute.getStatus(),
                jsonString
        );
    }
}
