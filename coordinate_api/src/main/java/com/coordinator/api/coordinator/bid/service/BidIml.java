package com.coordinator.api.coordinator.bid.service;

import com.coordinate.model.QueryOptions;
import com.coordinate.model.bids.BidPostRequest;
import com.coordinate.model.bids.ImmutableBidEntity;
import com.coordinate.model.coordinator.BidDto;
import com.coordinator.api.coordinator.bid.mappers.BidPostRequestToEntityMapper;
import com.coordinator.api.coordinator.bid.repository.ICoordinatorBidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BidIml implements IBid {
    @Autowired
    private ICoordinatorBidsRepository iCoordinatorBidsRepository;

    @Override
    public List<BidDto> getAllCoordinatorsBids(
            UUID coordinatorId,
            QueryOptions queryOptions) {
        return iCoordinatorBidsRepository.getAllCoordinatorsBids(coordinatorId, queryOptions);
    }

    @Override
    public void createBid(UUID coordinatorId, BidPostRequest bidPostRequest)  {
        boolean bidExists = iCoordinatorBidsRepository.bidExists(coordinatorId, UUID.fromString(bidPostRequest.getEventId()));

        if (bidExists) {
            throw new NullPointerException(
                    "A Bid already exists for coordinator: " + coordinatorId
            );
        }


        ImmutableBidEntity bidEntity = BidPostRequestToEntityMapper.mapBidRequestToEntity(bidPostRequest);

            iCoordinatorBidsRepository.createBid(coordinatorId, bidEntity);

    }
}
