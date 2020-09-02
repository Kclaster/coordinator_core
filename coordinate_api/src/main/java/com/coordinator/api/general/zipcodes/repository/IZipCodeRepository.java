package com.coordinator.api.general.zipcodes.repository;

import java.util.UUID;

public interface IZipCodeRepository {
    Boolean zipCodeExists(String zipcode);
    void createZipCode(UUID zipCodeId, String zipCode);
}
