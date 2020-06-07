package com.coordinator.core.controllers;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.services.ICoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coordinators")
public class CoordinatorController {
    @Autowired
    private ICoordinator iCoordinator;

    @GetMapping
    @RequestMapping("{coordinatorId}")
    public ResponseEntity<CoordinatorDto> getCoordinator(@PathVariable(value = "coordinatorId") String coordinatorId) {
        return ResponseEntity.ok(iCoordinator.getCoordinator(UUID.fromString(coordinatorId)));
    }
}
