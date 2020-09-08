package com.coordinate.model.event;

import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public class EventServicesPostRequest {
    private UUID id;

    @NotNull(message="shouldProvideFloral is required.")
    private Boolean shouldProvideFloral;
    private Boolean shouldProvideDress;
    private Boolean shouldProvidePartyAttire;
    private Boolean shouldProvideCatering;
    private Boolean shouldProvideSecurity;
    private Boolean shouldProvideBar;
    private Boolean shouldProvidePhotographer;
    private Boolean shouldProvideVideographer;
    private Boolean shouldProvideMusician;
    private Boolean shouldProvideCosmetician;
    private Boolean shouldProvideBabySitter;
    private Boolean shouldProvideOther;
    private Boolean shouldProvideBudgetPlanning;
    private Boolean shouldProvideCleanup;
    private Boolean shouldProvideSeatingChart;
    private Boolean shouldProvideScheduling;
    private Boolean shouldProvidePartyGifts;
    private Boolean shouldProvideInvitationsAndResponses;
    private List<ServiceDatePlace> serviceDatePlace;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getShouldProvideFloral() {
        return shouldProvideFloral;
    }

    public void setShouldProvideFloral(Boolean shouldProvideFloral) {
        this.shouldProvideFloral = shouldProvideFloral;
    }

    public Boolean getShouldProvideDress() {
        return shouldProvideDress;
    }

    public void setShouldProvideDress(Boolean shouldProvideDress) {
        this.shouldProvideDress = shouldProvideDress;
    }

    public Boolean getShouldProvidePartyAttire() {
        return shouldProvidePartyAttire;
    }

    public void setShouldProvidePartyAttire(Boolean shouldProvidePartyAttire) {
        this.shouldProvidePartyAttire = shouldProvidePartyAttire;
    }

    public Boolean getShouldProvideCatering() {
        return shouldProvideCatering;
    }

    public void setShouldProvideCatering(Boolean shouldProvideCatering) {
        this.shouldProvideCatering = shouldProvideCatering;
    }

    public Boolean getShouldProvideSecurity() {
        return shouldProvideSecurity;
    }

    public void setShouldProvideSecurity(Boolean shouldProvideSecurity) {
        this.shouldProvideSecurity = shouldProvideSecurity;
    }

    public Boolean getShouldProvideBar() {
        return shouldProvideBar;
    }

    public void setShouldProvideBar(Boolean shouldProvideBar) {
        this.shouldProvideBar = shouldProvideBar;
    }

    public Boolean getShouldProvidePhotographer() {
        return shouldProvidePhotographer;
    }

    public void setShouldProvidePhotographer(Boolean shouldProvidePhotographer) {
        this.shouldProvidePhotographer = shouldProvidePhotographer;
    }

    public Boolean getShouldProvideVideographer() {
        return shouldProvideVideographer;
    }

    public void setShouldProvideVideographer(Boolean shouldProvideVideographer) {
        this.shouldProvideVideographer = shouldProvideVideographer;
    }

    public Boolean getShouldProvideMusician() {
        return shouldProvideMusician;
    }

    public void setShouldProvideMusician(Boolean shouldProvideMusician) {
        this.shouldProvideMusician = shouldProvideMusician;
    }

    public Boolean getShouldProvideCosmetician() {
        return shouldProvideCosmetician;
    }

    public void setShouldProvideCosmetician(Boolean shouldProvideCosmetician) {
        this.shouldProvideCosmetician = shouldProvideCosmetician;
    }

    public Boolean getShouldProvideBabySitter() {
        return shouldProvideBabySitter;
    }

    public void setShouldProvideBabySitter(Boolean shouldProvideBabySitter) {
        this.shouldProvideBabySitter = shouldProvideBabySitter;
    }

    public Boolean getShouldProvideOther() {
        return shouldProvideOther;
    }

    public void setShouldProvideOther(Boolean shouldProvideOther) {
        this.shouldProvideOther = shouldProvideOther;
    }

    public Boolean getShouldProvideBudgetPlanning() {
        return shouldProvideBudgetPlanning;
    }

    public void setShouldProvideBudgetPlanning(Boolean shouldProvideBudgetPlanning) {
        this.shouldProvideBudgetPlanning = shouldProvideBudgetPlanning;
    }

    public Boolean getShouldProvideCleanup() {
        return shouldProvideCleanup;
    }

    public void setShouldProvideCleanup(Boolean shouldProvideCleanup) {
        this.shouldProvideCleanup = shouldProvideCleanup;
    }

    public Boolean getShouldProvideSeatingChart() {
        return shouldProvideSeatingChart;
    }

    public void setShouldProvideSeatingChart(Boolean shouldProvideSeatingChart) {
        this.shouldProvideSeatingChart = shouldProvideSeatingChart;
    }

    public Boolean getShouldProvideScheduling() {
        return shouldProvideScheduling;
    }

    public void setShouldProvideScheduling(Boolean shouldProvideScheduling) {
        this.shouldProvideScheduling = shouldProvideScheduling;
    }

    public Boolean getShouldProvidePartyGifts() {
        return shouldProvidePartyGifts;
    }

    public void setShouldProvidePartyGifts(Boolean shouldProvidePartyGifts) {
        this.shouldProvidePartyGifts = shouldProvidePartyGifts;
    }

    public Boolean getShouldProvideInvitationsAndResponses() {
        return shouldProvideInvitationsAndResponses;
    }

    public void setShouldProvideInvitationsAndResponses(Boolean shouldProvideInvitationsAndResponses) {
        this.shouldProvideInvitationsAndResponses = shouldProvideInvitationsAndResponses;
    }

    public List<ServiceDatePlace> getServiceDatePlace() {
        return serviceDatePlace;
    }

    public void setServiceDatePlace(List<ServiceDatePlace> serviceDatePlace) {
        this.serviceDatePlace = serviceDatePlace;
    }
}
