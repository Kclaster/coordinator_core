package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.coordinator.main.repository.ICoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.core.general.main.helpers.GeneralHelper.FirstOrDefault;

@Service
public class CoordinatorImpl implements ICoordinator {
    @Autowired
    private ICoordinatorRepository iCoordinatorRepository;

    @Autowired
    private ICoordinatorZipCode iCoordinatorZipCode;

    @Override
    public CoordinatorDto getCoordinator(UUID coordinatorId) {
        return iCoordinatorRepository.getCoordinator(coordinatorId);
    }

    @Override
    public void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException {
        iCoordinatorRepository.updateArchiveCoordinator(coordinatorId);
    }

    @Override
    public CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception {
        CoordinatorDto currentCoordinator = getCoordinator(coordinatorId);

        if (shouldUpdateCoordinatorZipCodeData(coordinatorPutRequest, currentCoordinator)) {
            iCoordinatorZipCode.UpdateCoordinatorsZipCode(coordinatorId, coordinatorPutRequest);
        }
        CoordinatorPutOrDefaultMapper(coordinatorPutRequest, currentCoordinator);

        try {
            iCoordinatorRepository.updateCoordinator(coordinatorId, coordinatorPutRequest);

            return iCoordinatorRepository.getCoordinator(coordinatorId);
        } catch (Exception e) {
            throw e;
        }
    }



    private void CoordinatorPutOrDefaultMapper(CoordinatorPutRequest coordinatorPutRequest, CoordinatorDto currentCoordinator) {
        coordinatorPutRequest.setTitle(FirstOrDefault(coordinatorPutRequest.getTitle(), currentCoordinator.getTitle()));
        coordinatorPutRequest.setOfficeState(FirstOrDefault(coordinatorPutRequest.getOfficeState(), currentCoordinator.getOfficeState()));
        coordinatorPutRequest.setOfficeAddress(FirstOrDefault(coordinatorPutRequest.getOfficeAddress(), currentCoordinator.getOfficeAddress()));
        coordinatorPutRequest.setOfficeCity(FirstOrDefault(coordinatorPutRequest.getOfficeCity(), currentCoordinator.getOfficeCity()));
        coordinatorPutRequest.setOfficePostalCode(FirstOrDefault(coordinatorPutRequest.getOfficePostalCode(), currentCoordinator.getOfficePostalCode()));
        coordinatorPutRequest.setContactEmail(FirstOrDefault(coordinatorPutRequest.getContactEmail(), currentCoordinator.getContactEmail()));
        coordinatorPutRequest.setMaximumDistanceToClient(FirstOrDefault(coordinatorPutRequest.getMaximumDistanceToClient(), currentCoordinator.getMaximumDistanceToClient()));
        coordinatorPutRequest.setLevelOneDefaultBid(FirstOrDefault(coordinatorPutRequest.getLevelOneDefaultBid(), currentCoordinator.getLevelOneDefaultBid()));
        coordinatorPutRequest.setLevelTwoDefaultBid(FirstOrDefault(coordinatorPutRequest.getLevelTwoDefaultBid(), currentCoordinator.getLevelTwoDefaultBid()));
        coordinatorPutRequest.setLevelThreeDefaultBid(FirstOrDefault(coordinatorPutRequest.getLevelThreeDefaultBid(), currentCoordinator.getLevelThreeDefaultBid()));
    }

    private boolean shouldUpdateCoordinatorZipCodeData(CoordinatorPutRequest coordinatorPutRequest, CoordinatorDto currentCoordinator) {
        boolean IsPostalCodeDataChanged =  coordinatorPutRequest
                    .getMaximumDistanceToClient() != currentCoordinator
                    .getMaximumDistanceToClient()
                    || coordinatorPutRequest
                    .getOfficePostalCode() != currentCoordinator
                    .getOfficePostalCode();

        boolean shouldUpdateCoordinatorZipCodeData = IsPostalCodeDataChanged &&
                coordinatorPutRequest.getMaximumDistanceToClient() > 0;

        return IsPostalCodeDataChanged && shouldUpdateCoordinatorZipCodeData;

    }

}
