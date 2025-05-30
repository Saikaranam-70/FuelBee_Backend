package com.FuelBee.backend.exception;

public class DuplicateFuelTypeException extends Throwable{
    public DuplicateFuelTypeException(String duplicateFuelTypeException){
        super(duplicateFuelTypeException);
    }
}
