package com.coordinate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCodes {
    @JsonProperty("zip_codes")
    private ZipCode[] zipCodes;

    public List<ZipCode> getZipCodes() {
        return Arrays.asList(zipCodes);
    }

    public void setZipCodes(ZipCode[] zipCodes) {
        this.zipCodes = zipCodes;
    }
}
