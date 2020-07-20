package com.coordinator.core.users.bids.services;

import com.coordinator.core.general.main.models.QueryOptions;
import com.coordinator.core.users.bids.models.BidDto;
import com.coordinator.core.users.bids.repository.IEventBidsRepository;
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
