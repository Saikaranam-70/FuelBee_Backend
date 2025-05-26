package com.FuelBee.backend.model.dto;

import lombok.Data;

@Data
public class PhoneLoginRequest {
    private String phoneNumber;

    public PhoneLoginRequest(String phone){
        this.phoneNumber = phone;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
