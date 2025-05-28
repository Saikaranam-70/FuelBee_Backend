package com.FuelBee.backend.exception;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String userNotFoundException){
        super(userNotFoundException);
    }
}
