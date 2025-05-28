package com.FuelBee.backend.exception;

public class FuelStationNotFoundException extends Throwable{
    public FuelStationNotFoundException(String fuelStationNotFoundException){
        super(fuelStationNotFoundException);
    }
}
