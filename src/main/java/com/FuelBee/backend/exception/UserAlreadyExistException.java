package com.FuelBee.backend.exception;

public class UserAlreadyExistException extends Throwable{
    public UserAlreadyExistException(String userAlreadyExists){
        super(userAlreadyExists);
    }
}
