package com.coordinator.core.users.event.controller;

import com.coordinator.core.users.event.models.EventDto;
import com.coordinator.core.users.event.models.EventPostRequest;
import com.coordinator.core.users.event.service.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/events")
public class EventController {
    @Autowired
    private IEvent iEvent;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_COORDINATOR, ROLE_USER')")
    public ResponseEntity<EventDto> getEvent(@PathVariable(value = "userId") String userId,
                                             @RequestParam Map<String, String> queryOptions) {
        return ResponseEntity.ok(iEvent.getEvent(UUID.fromString(userId)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_COORDINATOR, ROLE_USER')")
    public ResponseEntity createEvent(
            @PathVariable(value = "userId") String userId,
            @Valid @RequestBody final EventPostRequest eventPostRequest,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            ResponseEntity.badRequest().body(errors.getFieldErrors());
        }
        iEvent.createEvent(UUID.fromString(userId), eventPostRequest);

        return ResponseEntity.ok().build();
    }
}
