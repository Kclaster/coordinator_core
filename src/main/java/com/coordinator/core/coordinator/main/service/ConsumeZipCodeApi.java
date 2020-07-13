package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.ZipCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ConsumeZipCodeApi {
    @Value("${ZIPCODE_API_KEY}")
    private static String zipCodeApiKey;

    private static final Logger log = LoggerFactory.getLogger(ConsumeZipCodeApi.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumeZipCodeApi.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            ZipCodes zipCodes = restTemplate.getForObject(
                    "https://gturnquist-quoters.cfapps.io/api/random", ZipCodes.class);
            log.info(zipCodes.toString());
        };
    }

    public static ZipCodes FetchZipCodesByDistance(RestTemplate restTemplate, String initialZipCode, String distanceWillingToTravel) throws Exception {
        ZipCodes zipCodes = restTemplate.getForObject(
                String.format("https://www.zipcodeapi.com/rest/%s/radius.json/%s/%s/mile", zipCodeApiKey, initialZipCode, distanceWillingToTravel), ZipCodes.class);
    return zipCodes;
        }
    }
