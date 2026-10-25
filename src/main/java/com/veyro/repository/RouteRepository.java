package com.veyro.repository;

import com.veyro.enums.Method;
import com.veyro.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByMethodAndPathAndProjectIdentifier(Method method, String path, String projectIdentifier);
}
