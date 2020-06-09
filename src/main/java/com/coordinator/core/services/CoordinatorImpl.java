package com.coordinator.core.services;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.models.CoordinatorPutRequest;
import com.coordinator.core.repository.core.ICoordinatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoordinatorImpl implements ICoordinator {
    @Autowired
    private ICoordinatorsRepository iCoordinatorsRepository;

    @Override
    public CoordinatorDto getCoordinator(UUID coordinatorId) {
        return iCoordinatorsRepository.getCoordinator(coordinatorId);
    }

    @Override
    public void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException {
        iCoordinatorsRepository.updateArchiveCoordinator(coordinatorId);
    }

    @Override
    public CoordinatorDto createCoordinator(String username) {
        UUID coordinatorId = UUID.randomUUID();
        iCoordinatorsRepository.createCoordinator(coordinatorId, username);

       return iCoordinatorsRepository.getCoordinator(coordinatorId);
    }

    @Override
    public CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws IllegalStateException {
        CoordinatorDto currentCoordinator = iCoordinatorsRepository.getCoordinator(coordinatorId);
        coordinatorPutRequest.setTitle(coordinatorPutRequest.getTitle() == null ? currentCoordinator.getTitle() : coordinatorPutRequest.getTitle());
        coordinatorPutRequest.setOfficeState(coordinatorPutRequest.getOfficeState() == null ? currentCoordinator.getOfficeState() : coordinatorPutRequest.getOfficeState());
        coordinatorPutRequest.setOfficeAddress(coordinatorPutRequest.getOfficeAddress() == null ? currentCoordinator.getOfficeAddress() : coordinatorPutRequest.getOfficeAddress());
        coordinatorPutRequest.setOfficeCity(coordinatorPutRequest.getOfficeCity() == null ? currentCoordinator.getOfficeCity() : coordinatorPutRequest.getOfficeCity());
        coordinatorPutRequest.setOfficePostalCode(coordinatorPutRequest.getOfficePostalCode() == null ? currentCoordinator.getOfficePostalCode() : coordinatorPutRequest.getOfficePostalCode());
        coordinatorPutRequest.setContactEmail(coordinatorPutRequest.getContactEmail() == null ? currentCoordinator.getContactEmail() : coordinatorPutRequest.getContactEmail());
        coordinatorPutRequest.setMaximumDistanceToClient(coordinatorPutRequest.getMaximumDistanceToClient() == null ? currentCoordinator.getMaxDistanceToClient() : coordinatorPutRequest.getMaximumDistanceToClient());
        coordinatorPutRequest.setLevelOneDefaultBid(coordinatorPutRequest.getLevelOneDefaultBid() == null ? currentCoordinator.getLevelOneDefaultBid() : coordinatorPutRequest.getLevelOneDefaultBid());
        coordinatorPutRequest.setLevelTwoDefaultBid(coordinatorPutRequest.getLevelTwoDefaultBid() == null ? currentCoordinator.getLevelTwoDefaultBid() : coordinatorPutRequest.getLevelTwoDefaultBid());
        coordinatorPutRequest.setLevelThreeDefaultBid(coordinatorPutRequest.getLevelThreeDefaultBid() == null ? currentCoordinator.getLevelThreeDefaultBid() : coordinatorPutRequest.getLevelThreeDefaultBid());
        coordinatorPutRequest.setUsername(coordinatorPutRequest.getUsername() == null ? currentCoordinator.getUsername() : coordinatorPutRequest.getUsername());

        try {
            iCoordinatorsRepository.updateCoordinator(coordinatorId, coordinatorPutRequest);
            // Return updated coordinator after put request.
            return iCoordinatorsRepository.getCoordinator(coordinatorId);
        } catch (Exception e) {
            throw e;
        }
    }

}
