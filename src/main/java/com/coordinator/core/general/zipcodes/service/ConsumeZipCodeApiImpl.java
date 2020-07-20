package com.coordinator.core.general.zipcodes.service;

import com.coordinator.core.general.zipcodes.models.ZipCodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class ConsumeZipCodeApiImpl {
    @Value("${ZIPCODE_API_KEY}")
    private static String zipCodeApiKey;

    public static ZipCodes FetchZipCodesByDistance(
            String initialZipCode,
            Integer distanceWillingToTravel
    ) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        ZipCodes zipCodes = restTemplate.getForObject(
                String.format("https://www.zipcodeapi.com/rest/%s/radius.json/%s/%s/mile", "k1HLurzI5zTl8SgnwAA2pONnKvY84r5kWRO73wi4wlFzl9BLA8P4Z762Nek8VoRI", initialZipCode, distanceWillingToTravel), ZipCodes.class);
    return zipCodes;
        }
    }
