package com.FuelBee.backend.model.dto;

import com.FuelBee.backend.model.Entity.Address;
import lombok.Data;

@Data
public class AddAddressRequest {
    private String phone;
    private Address address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
