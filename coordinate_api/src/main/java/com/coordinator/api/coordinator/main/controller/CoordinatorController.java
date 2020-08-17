package com.coordinator.api.coordinator.main.controller;

import com.coordinate.model.CoordinatorDto;
import com.coordinate.model.CoordinatorPutRequest;
import com.coordinator.api.coordinator.main.service.ICoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coordinators")
public class CoordinatorController {
    @Autowired
    private ICoordinator iCoordinator;

    @GetMapping
    @RequestMapping("{coordinatorId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_COORDINATOR, ROLE_USER')")
    public ResponseEntity<CoordinatorDto> getCoordinator(@PathVariable(value = "coordinatorId") String coordinatorId) {
        return ResponseEntity.ok(iCoordinator.getCoordinator(UUID.fromString(coordinatorId)));
    }

    @DeleteMapping
    @RequestMapping("{coordinatorId}/delete")
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    public ResponseEntity updateArchiveCoordinator(@PathVariable(value = "coordinatorId") String coordinatorId) {
        try {
            iCoordinator.updateArchiveCoordinator(UUID.fromString(coordinatorId));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @RequestMapping("{coordinatorId}/update")
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    public ResponseEntity<?> updateCoordinator(@PathVariable(value = "coordinatorId") String coordinatorId,
                                                            @RequestBody CoordinatorPutRequest coordinatorPutRequest) {

        try {
            return ResponseEntity.ok(iCoordinator.updateCoordinator(UUID.fromString(coordinatorId), coordinatorPutRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
