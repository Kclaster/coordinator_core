package com.coordinator.api.coordinator.event.controller;

import com.coordinate.model.coordinator.CoordinatorEventDto;
import com.coordinator.api.coordinator.event.service.ICoordinatorEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.coordinator.api.general.main.helpers.QueryOptionsHelper.fromQueryParameters;

@RestController
@RequestMapping("/api/v1/coordinators/{coordinatorId}/events")
public class CoordinatorEventController {
    @Autowired
    private ICoordinatorEvents iCoordinatorEvents;

    @GetMapping
    public ResponseEntity<List<CoordinatorEventDto>> getAllCoordinatorEventsByZipCode(@PathVariable(value = "coordinatorId") String coordinatorId,
                                                                                      @RequestParam Map<String, String> queryOptions) {
        return ResponseEntity.ok(iCoordinatorEvents.getAllCoordinatorEventsByZipCode(
                UUID.fromString(coordinatorId),
                fromQueryParameters(queryOptions)
        ));

    }
}
