package com.coordinator.core.users.bids.controllers;

import com.coordinator.core.users.bids.models.BidDto;
import com.coordinator.core.users.bids.services.IBids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.coordinator.core.general.main.helpers.QueryOptionsHelper.fromQueryParameters;

@RestController
@RequestMapping("/api/v1/users/{userId}/events/{eventId}/bids")
public class BidsController {
    @Autowired
    private IBids iBids;

    @GetMapping
    public ResponseEntity<List<BidDto>> getAllCoordinatorsBids(@PathVariable(value = "eventId") String eventId,
                                                               @RequestParam Map<String, String> queryOptions) {
        return ResponseEntity.ok(iBids.getAllBidsByEvent(
                UUID.fromString(eventId),
                fromQueryParameters(queryOptions)
        ));
    }
}
