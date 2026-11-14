package com.veyro.controller;

import com.veyro.dto.RouteRequestDTO;
import com.veyro.dto.RouteResponseDTO;
import com.veyro.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectIdentifier}/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<RouteResponseDTO> create(@PathVariable String projectIdentifier, @RequestBody @Valid RouteRequestDTO dto) {

        RouteResponseDTO created = routeService.createRoute(dto, projectIdentifier);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
