package com.coordinator.core.services;

import com.coordinator.core.models.BidDto;
import com.coordinator.core.models.QueryOptions;
import com.coordinator.core.repository.core.IBidsRepository;
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
