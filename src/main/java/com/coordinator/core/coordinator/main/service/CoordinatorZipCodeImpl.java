package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.coordinator.main.repository.ICoordinatorRepository;
import com.coordinator.core.general.zipcodes.models.ZipCode;
import com.coordinator.core.general.zipcodes.models.ZipCodes;
import com.coordinator.core.general.zipcodes.repository.IZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.core.general.zipcodes.service.ConsumeZipCodeApiImpl.FetchZipCodesByDistance;

@Service
public class CoordinatorZipCodeImpl implements ICoordinatorZipCode {
    @Autowired
    private ICoordinatorRepository iCoordinatorRepository;

    @Autowired
    IZipCodeRepository iZipCodeRepository;

    @Override
    public void UpdateCoordinatorsZipCode(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception {
        try {
            iCoordinatorRepository.deleteCoordinatorsZipCodes(coordinatorId);
            ZipCodes zipCodes = FetchZipCodesByDistance(
                    coordinatorPutRequest.getOfficePostalCode(),
                    coordinatorPutRequest.getMaximumDistanceToClient()
            );

            InsertZipCodesToCoordinators(coordinatorId, zipCodes);
        } catch (Exception e) {
            throw e;
        }
    }

    private void InsertZipCodesToCoordinators(UUID coordinatorId, ZipCodes zipCodes) {
        zipCodes.getZipCodes()
                .stream()
                .map(ZipCode::getZipCode)
                .forEach(zipCode -> {
                    UUID newZipUUID = UUID.randomUUID();
                    if (!iZipCodeRepository.zipCodeExists(zipCode)) {
                        iZipCodeRepository.createZipCode(newZipUUID, zipCode);
                    }
                    iCoordinatorRepository.createCoordinatorsZipCodes(UUID.randomUUID(), newZipUUID, coordinatorId);
                });
    }
}
