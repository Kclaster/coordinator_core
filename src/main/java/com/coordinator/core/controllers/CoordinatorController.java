package com.coordinator.core.controllers;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.models.CoordinatorPostRequest;
import com.coordinator.core.models.CoordinatorPutRequest;
import com.coordinator.core.services.ICoordinator;
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

    // PreAuthorize -- hasRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

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

    @PostMapping
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    public ResponseEntity<CoordinatorDto> createCoordinator(@RequestBody final CoordinatorPostRequest coordinatorPostRequest) {
        return ResponseEntity.ok(iCoordinator.createCoordinator(coordinatorPostRequest.getUsername()));
    }

    @PutMapping
    @RequestMapping("{coordinatorId}/update")
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")

    public ResponseEntity<CoordinatorDto> updateCoordinator(@PathVariable(value = "coordinatorId") String coordinatorId,
                                                            @RequestBody CoordinatorPutRequest coordinatorPutRequest) {

        return ResponseEntity.ok(iCoordinator.updateCoordinator(UUID.fromString(coordinatorId), coordinatorPutRequest));
    }
}
