package com.coordinator.core.models;

import java.util.UUID;

public class CoordinatorDto {
    private UUID id;
    private String title;
    private String officeState;
    private String officeAddress;
    private String contactEmail;
    private Integer maxDistanceToClient;
    private Integer levelOneDefaultBid;
    private Integer levelTwoDefaultBid;
    private Integer levelThreeDefaultBid;

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
}
