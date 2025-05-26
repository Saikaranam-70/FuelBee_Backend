package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @Value("${app.otp.expiry-minutes}")
    private int otpExpiryTime;

    public String generateOtp(){
        return RandomStringUtils.randomNumeric(6);
    }

    public void sendPhoneOtp(String phoneNumber, String otp){
        try{
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    "Your FuelBee Verification Code is: "+ otp
            ).create();
            User user = userRepository.findByPhone(phoneNumber);
            user.setOtp(otp);
            System.out.println(otp);
            userRepository.save(user);


        }catch (Exception e){
            System.out.println("OTP for "+phoneNumber+": "+otp);
        }
    }

    public boolean validatePhoneOtp(String phoneNumber, String otp){
        User user = userRepository.findByPhone(phoneNumber);
        if(user.getOtp()==null || user.getOtpExpiryTime()==null){
            return false;
        }

        if(!user.getOtp().equals(otp)){
            return false;
        }
        if(user.getOtpExpiryTime().before(new Date())){
            return false;
        }

        user.setVerified(true);
        user.setOtp(null);
        user.setOtpExpiryTime(null);
        return true;

    }




}
