package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.coordinator.main.repository.ICoordinatorRepository;
import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.core.general.helpers.GeneralHelper.FirstOrDefault;

@Service
public class CoordinatorImpl implements ICoordinator {
    @Autowired
    private ICoordinatorRepository iCoordinatorsRepository;

    @Override
    public CoordinatorDto getCoordinator(UUID coordinatorId) {
        return iCoordinatorsRepository.getCoordinator(coordinatorId);
    }

    @Override
    public void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException {
        iCoordinatorsRepository.updateArchiveCoordinator(coordinatorId);
    }

    @Override
    public CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws IllegalStateException {
        /* Check if distance or initial zipcode changed
            if yes {
             get list of zipcodes within distance of initialZipcode

                loop through each and check if zip exists/ insert otherwise
                insert into join table
            }


            ** This shouldn't mess with the get Coordinator. The Get zipcodes within distance should only be used for checking if event is valid.
        */
        CoordinatorDto currentCoordinator = iCoordinatorsRepository.getCoordinator(coordinatorId);
        coordinatorPutRequest.setTitle(FirstOrDefault(coordinatorPutRequest.getTitle(), currentCoordinator.getTitle()));
        coordinatorPutRequest.setOfficeState(FirstOrDefault(coordinatorPutRequest.getOfficeState(), currentCoordinator.getOfficeState()));
        coordinatorPutRequest.setOfficeAddress(FirstOrDefault(coordinatorPutRequest.getOfficeAddress(), currentCoordinator.getOfficeAddress()));
        coordinatorPutRequest.setOfficeCity(FirstOrDefault(coordinatorPutRequest.getOfficeCity(), currentCoordinator.getOfficeCity()));
        coordinatorPutRequest.setOfficePostalCode(FirstOrDefault(coordinatorPutRequest.getOfficePostalCode(), currentCoordinator.getOfficePostalCode()));
        coordinatorPutRequest.setContactEmail(FirstOrDefault(coordinatorPutRequest.getContactEmail(), currentCoordinator.getContactEmail()));
        coordinatorPutRequest.setMaximumDistanceToClient(FirstOrDefault(coordinatorPutRequest.getMaximumDistanceToClient(), currentCoordinator.getMaxDistanceToClient()));
        coordinatorPutRequest.setLevelOneDefaultBid(FirstOrDefault(coordinatorPutRequest.getLevelOneDefaultBid(), currentCoordinator.getLevelOneDefaultBid()));
        coordinatorPutRequest.setLevelTwoDefaultBid(FirstOrDefault(coordinatorPutRequest.getLevelTwoDefaultBid(), currentCoordinator.getLevelTwoDefaultBid()));
        coordinatorPutRequest.setLevelThreeDefaultBid(FirstOrDefault(coordinatorPutRequest.getLevelThreeDefaultBid(), currentCoordinator.getLevelThreeDefaultBid()));
        coordinatorPutRequest.setUsername(FirstOrDefault(coordinatorPutRequest.getUsername(), currentCoordinator.getUsername()));

        try {
            iCoordinatorsRepository.updateCoordinator(coordinatorId, coordinatorPutRequest);
            // Return updated coordinator after put request.
            return iCoordinatorsRepository.getCoordinator(coordinatorId);
        } catch (Exception e) {
            throw e;
        }
    }

}
