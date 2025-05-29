package com.FuelBee.backend.model.dto;

import com.FuelBee.backend.model.Entity.FuelStation;

public class FuelStationDto {
    private String dealerId;
    private FuelStation fuelStation;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }
}
