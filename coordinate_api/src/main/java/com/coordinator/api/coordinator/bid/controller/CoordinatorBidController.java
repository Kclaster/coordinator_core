package com.coordinator.api.coordinator.bid.controller;

import com.coordinate.model.coordinator.CoordinatorBidDto;
import com.coordinate.model.bids.BidPostRequest;
import com.coordinator.api.coordinator.bid.service.ICoordinatorBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.coordinator.api.general.main.helpers.QueryOptionsHelper.fromQueryParameters;

@RestController
@RequestMapping("/api/v1/coordinators/{coordinatorId}/bids")
public class CoordinatorBidController {
    @Autowired
    private ICoordinatorBid iCoordinatorBid;

    @GetMapping
    public ResponseEntity<List<CoordinatorBidDto>> getAllCoordinatorsBids(@PathVariable(value = "coordinatorId") String coordinatorId,
                                                                          @RequestParam Map<String, String> queryOptions) {
        return ResponseEntity.ok(iCoordinatorBid.getAllCoordinatorsBids(
                UUID.fromString(coordinatorId),
                fromQueryParameters(queryOptions)
        ));
    }

    @PostMapping
    public ResponseEntity<Void> createBid(@PathVariable(value = "coordinatorId") String coordinatorId,
                                          @Valid @RequestBody final BidPostRequest postRequest,
                                          Errors errors) {
        if (errors.hasErrors()) {
            ResponseEntity.badRequest().body(errors.getFieldErrors());
        }

        iCoordinatorBid.createBid(UUID.fromString(coordinatorId), postRequest);

        return ResponseEntity.ok().build();
    }
}
