package com.coordinator.core.controllers;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.models.EventPostRequest;
import com.coordinator.core.services.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/events")
public class EventController {
    @Autowired
    private IEvent iEvent;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_COORDINATOR, ROLE_USER')")
    public ResponseEntity<CoordinatorDto> createCoordinator(
            @PathVariable(value = "userId") String userId,
            @Valid @RequestBody final EventPostRequest eventPostRequest,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            ResponseEntity.badRequest().body(errors.getFieldError());
        }
        iEvent.createEvent(UUID.fromString(userId), eventPostRequest);

        return ResponseEntity.ok().build();
    }
}
