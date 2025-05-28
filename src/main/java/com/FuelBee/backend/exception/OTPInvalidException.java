package com.FuelBee.backend.exception;

public class OTPInvalidException extends Throwable{
    public OTPInvalidException(String otpInvalidException){
        super(otpInvalidException);
    }
}
