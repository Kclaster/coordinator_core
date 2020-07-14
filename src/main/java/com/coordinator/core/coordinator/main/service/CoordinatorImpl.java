package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.general.zipcodes.models.ZipCodes;
import com.coordinator.core.coordinator.main.repository.ICoordinatorRepository;
import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.core.general.zipcodes.service.ConsumeZipCodeApiImpl.FetchZipCodesByDistance;
import static com.coordinator.core.general.main.helpers.GeneralHelper.FirstOrDefault;

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
        // add this to a helper
        CoordinatorDto currentCoordinator = getCoordinator(coordinatorId);
        boolean postalCodeDataChanged = coordinatorPutRequest
                .getMaximumDistanceToClient() != currentCoordinator
                .getMaxDistanceToClient()
                || coordinatorPutRequest
                .getOfficePostalCode() != currentCoordinator
                .getOfficePostalCode();
        if (
                postalCodeDataChanged &&
                        coordinatorPutRequest.getMaximumDistanceToClient() > 0
        ) {
            try {
                ZipCodes zipCodes = FetchZipCodesByDistance(
                        coordinatorPutRequest.getOfficePostalCode(),
                        coordinatorPutRequest.getMaximumDistanceToClient()
                );

                zipCodes.getZipCodes()
                        .stream()
                        .forEach(zipCode -> {
                            //loop through each and check if zip exists/ insert otherwise
                            // insert into join table
                        });
                System.out.println(zipCodes);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // add this to a mapper
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

        try {
            iCoordinatorsRepository.updateCoordinator(coordinatorId, coordinatorPutRequest);
            // Return updated coordinator after put request.
            return iCoordinatorsRepository.getCoordinator(coordinatorId);
        } catch (Exception e) {
            throw e;
        }
    }

}
