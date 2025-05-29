package com.FuelBee.backend.model.dto;

import com.FuelBee.backend.model.Entity.FuelInfo;
import com.FuelBee.backend.model.Entity.FuelStation;

public class FuelInfoDto {
    private String fuelStationId;
    private FuelInfo fuelInfo;

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public FuelInfo getFuelInfo() {
        return fuelInfo;
    }

    public void setFuelInfo(FuelInfo fuelInfo) {
        this.fuelInfo = fuelInfo;
    }
}
