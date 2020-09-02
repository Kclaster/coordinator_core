package com.coordinator.api.users.bids.services;

import com.coordinate.model.QueryOptions;
import com.coordinate.model.bids.BidDto;
import com.coordinator.api.users.bids.repository.IEventBidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BidsImpl implements IBids {
    @Autowired
    private IEventBidsRepository iEventBidsRepository;

    @Override
    public List<BidDto> getAllBidsByEvent(
            UUID eventId,
            QueryOptions queryOptions
    ) {
        return iEventBidsRepository.getAllEventBids(eventId, queryOptions);
    }
}
