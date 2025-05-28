package com.FuelBee.backend.exception;

public class AddressNotFoundException extends Throwable{
    public AddressNotFoundException(String addressNotFoundException){
        super(addressNotFoundException);
    }
}
