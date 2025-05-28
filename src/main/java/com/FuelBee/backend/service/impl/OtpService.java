package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.repository.DealerRepository;
import com.FuelBee.backend.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealerRepository dealerRepository;

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
            Optional<User> optionalUser = userRepository.findByPhone(phoneNumber);
            User user = optionalUser.get();
            user.setOtp(otp);

            long expiryTime = System.currentTimeMillis()+60000;
            user.setOtpExpiryTime(new Date(expiryTime));
            System.out.println(otp);
            userRepository.save(user);


        }catch (Exception e){
            System.out.println("OTP for "+phoneNumber+": "+otp);
        }
    }

    public boolean validatePhoneOtp(String phoneNumber, String otp){
        Optional<User> optionalUser = userRepository.findByPhone(phoneNumber);
        System.out.println("called");
        User user = optionalUser.get();
        System.out.println(otp);
        System.out.println(user.getOtp());
        if(user.getOtp()==null || user.getOtpExpiryTime()==null){
            System.out.println("Called this one");
            return false;
        }

        if(!user.getOtp().equals(otp)){
            System.out.println("called it");
            return false;

        }
        if(user.getOtpExpiryTime().before(new Date())){
            System.out.println("Called this");
            return false;
        }

        user.setVerified(true);
        user.setOtp(null);
        user.setOtpExpiryTime(null);
        userRepository.save(user);
        System.out.println("Called this for ");
        return true;

    }

    public void sendDealerPhoneOtp(String phoneNumber, String otp){
        try{
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    "Your FuelBee Verification Code is: "+ otp
            ).create();
            Optional<Dealer> optionalDealer = dealerRepository.findByPhone(phoneNumber);
            Dealer dealer = optionalDealer.get();
            dealer.setOtp(otp);

            long expiryTime = System.currentTimeMillis()+60000;
            dealer.setOtpExpiryTime(new Date(expiryTime));
            System.out.println(otp);
            dealerRepository.save(dealer);


        }catch (Exception e){
            System.out.println("OTP for "+phoneNumber+": "+otp);
        }
    }

    public boolean validateDealerPhoneOtp(String phoneNumber, String otp){
        Optional<Dealer> optionalDealer = dealerRepository.findByPhone(phoneNumber);
        System.out.println("called");
        Dealer dealer = optionalDealer.get();
        System.out.println(otp);
        System.out.println(dealer.getOtp());
        if(dealer.getOtp()==null || dealer.getOtpExpiryTime()==null){
            System.out.println("Called this one");
            return false;
        }

        if(!dealer.getOtp().equals(otp)){
            System.out.println("called it");
            return false;

        }
        if(dealer.getOtpExpiryTime().before(new Date())){
            System.out.println("Called this");
            return false;
        }

        dealer.setVerified(true);
        dealer.setOtp(null);
        dealer.setOtpExpiryTime(null);
        dealerRepository.save(dealer);
        System.out.println("Called this for ");
        return true;

    }




}
