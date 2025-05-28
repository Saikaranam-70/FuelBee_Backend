package com.FuelBee.backend.exception;

public class DealerNotFoundException extends Throwable{
    public DealerNotFoundException(String dealerNotFoundException){
        super(dealerNotFoundException);
    }
}
