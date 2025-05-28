package com.FuelBee.backend.exception;

public class UserNotVerifiedException extends Throwable{
    public UserNotVerifiedException(String userNotVerifiedException){
        super(userNotVerifiedException);
    }
}
