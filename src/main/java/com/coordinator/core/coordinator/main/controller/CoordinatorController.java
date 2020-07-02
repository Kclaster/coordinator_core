package com.coordinator.core.coordinator.main.controller;

import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import com.coordinator.core.coordinator.main.models.CoordinatorPostRequest;
import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.coordinator.main.service.ICoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    public ResponseEntity<CoordinatorDto> createCoordinator(@Valid @RequestBody final CoordinatorPostRequest coordinatorPostRequest, Errors errors) {
        if (errors.hasErrors()) {
            ResponseEntity.badRequest().body(errors.getFieldErrors());
        }
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
