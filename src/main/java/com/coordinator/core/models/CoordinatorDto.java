package com.coordinator.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CoordinatorDto {
    private UUID id;
    private String title;
    private String officeState;
    private String officeAddress;
    private String officeCity;
    private String officePostalCode;
    private String contactEmail;
    private String username;
    private Integer maxDistanceToClient;
    private Integer levelOneDefaultBid;
    private Integer levelTwoDefaultBid;
    private Integer levelThreeDefaultBid;
    private boolean isArchived;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfficeState() {
        return officeState;
    }

    public void setOfficeState(String officeState) {
        this.officeState = officeState;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getMaxDistanceToClient() {
        return maxDistanceToClient;
    }

    public void setMaxDistanceToClient(Integer maxDistanceToClient) {
        this.maxDistanceToClient = maxDistanceToClient;
    }

    public Integer getLevelOneDefaultBid() {
        return levelOneDefaultBid;
    }

    public void setLevelOneDefaultBid(Integer levelOneDefaultBid) {
        this.levelOneDefaultBid = levelOneDefaultBid;
    }

    public Integer getLevelTwoDefaultBid() {
        return levelTwoDefaultBid;
    }

    public void setLevelTwoDefaultBid(Integer levelTwoDefaultBid) {
        this.levelTwoDefaultBid = levelTwoDefaultBid;
    }

    public Integer getLevelThreeDefaultBid() {
        return levelThreeDefaultBid;
    }

    public void setLevelThreeDefaultBid(Integer levelThreeDefaultBid) {
        this.levelThreeDefaultBid = levelThreeDefaultBid;
    }

    @JsonProperty(value="isArchived")
    public boolean getIsArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getOfficePostalCode() {
        return officePostalCode;
    }

    public void setOfficePostalCode(String officePostalCode) {
        this.officePostalCode = officePostalCode;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOfficeCity() {
        return officeCity;
    }

    public void setOfficeCity(String officeCity) {
        this.officeCity = officeCity;
    }
}
