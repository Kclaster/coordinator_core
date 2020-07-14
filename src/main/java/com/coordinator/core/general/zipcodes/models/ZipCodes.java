package com.coordinator.core.general.zipcodes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCodes {
    @JsonProperty("zip_codes")
    private Collection<ZipCode> zipCodes;

    public Collection<ZipCode> getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(Collection<ZipCode> zipCodes) {
        this.zipCodes = zipCodes;
    }
}
