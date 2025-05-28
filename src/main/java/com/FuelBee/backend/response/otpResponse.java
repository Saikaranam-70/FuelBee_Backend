package com.FuelBee.backend.response;

public class otpResponse {
    private String message;
    private int statusCode;
    private String phoneNumber;

    public otpResponse(String message, int statusCode, String phoneNumber) {
        this.message = message;
        this.statusCode = statusCode;
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
