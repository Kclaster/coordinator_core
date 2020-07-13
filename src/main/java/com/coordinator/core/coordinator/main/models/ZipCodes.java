package com.coordinator.core.coordinator.main.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCodes {
    @JsonProperty("zip_codes")
    private ZipCode[] zipCodes;

    public ZipCode[] getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(ZipCode[] zipCodes) {
        this.zipCodes = zipCodes;
    }
}
