package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.User;

import com.FuelBee.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private OtpService otpService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByPhone(String phoneNumber) {
        return userRepository.findByPhone(phoneNumber);
    }

    @Override
    public User saveUser(String phoneNumber) {
        String otp = otpService.generateOtp();
        otpService.sendPhoneOtp(phoneNumber, otp);

        User user = new User();
        user.setPhone(phoneNumber);
        user.setOtp(otp);
        return userRepository.save(user);
    }


}
