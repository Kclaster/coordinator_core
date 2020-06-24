package com.coordinator.core.coordinator.bid.service;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.general.models.QueryOptions;
import com.coordinator.core.coordinator.bid.repository.IBidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BidIml implements IBid {
    @Autowired
    private IBidsRepository iBidsRepository;

    @Override
    public List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions) {
        return iBidsRepository.getAllCoordinatorsBids(coordinatorId, queryOptions);
    }
}
