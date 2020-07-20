package com.coordinator.core.coordinator.bid.service;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.coordinator.bid.models.BidPostRequest;
import com.coordinator.core.coordinator.bid.models.ImmutableBidEntity;
import com.coordinator.core.coordinator.bid.repository.ICoordinatorBidsRepository;
import com.coordinator.core.general.main.models.QueryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.coordinator.core.coordinator.bid.mappers.BidPostRequestToEntityMapper.mapBidRequestToEntity;

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


        ImmutableBidEntity bidEntity = mapBidRequestToEntity(bidPostRequest);

            iCoordinatorBidsRepository.createBid(coordinatorId, bidEntity);

    }
}
