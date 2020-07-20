package com.coordinator.core.coordinator.bid.controller;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.coordinator.bid.models.BidPostRequest;
import com.coordinator.core.coordinator.bid.service.IBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.coordinator.core.general.main.helpers.QueryOptionsHelper.fromQueryParameters;

@RestController
@RequestMapping("/api/v1/coordinators/{coordinatorId}/bids")
public class BidController {
    @Autowired
    private IBid iBid;

    @GetMapping
    public ResponseEntity<List<BidDto>> getAllCoordinatorsBids(@PathVariable(value = "coordinatorId") String coordinatorId,
                                                               @RequestParam Map<String, String> queryOptions) {
        return ResponseEntity.ok(iBid.getAllCoordinatorsBids(
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

        iBid.createBid(UUID.fromString(coordinatorId), postRequest);

        return ResponseEntity.ok().build();
    }
}
