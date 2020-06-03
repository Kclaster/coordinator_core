package com.coordinator.core.controllers;

import com.coordinator.core.models.BidDto;
import com.coordinator.core.models.QueryOptions;
import com.coordinator.core.services.IBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coordinators/{coordinatorId}/bids")
public class BidController {
    @Autowired
    private IBid iBid;

    @GetMapping
    public ResponseEntity<List<BidDto>> getAllCoordinatorsBids(@PathVariable(value = "coordinatorId") String coordinatorId,
                                                               @RequestBody final QueryOptions queryOptions) {
        return ResponseEntity.ok(iBid.getAllCoordinatorsBids(UUID.fromString(coordinatorId), queryOptions));
    }
}