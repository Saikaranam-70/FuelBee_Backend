package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.exception.UserNotFoundException;
import com.FuelBee.backend.exception.UserNotVerifiedException;
import com.FuelBee.backend.model.Entity.Address;
import com.FuelBee.backend.model.Entity.User;

import com.FuelBee.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private OtpService otpService;
    @Autowired
    private UserRepository userRepository;



    @Override
    public Optional<User> findByPhone(String phoneNumber) {
        return userRepository.findByPhone(phoneNumber);
    }

    @Override
    public User saveUser(String phoneNumber) {
        String otp = otpService.generateOtp();
        otpService.sendPhoneOtp(phoneNumber, otp);

        User user = new User();
        user.setPhone(phoneNumber);
        user.setOtp(otp);
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String phone) {
        String otp = otpService.generateOtp();
        otpService.sendPhoneOtp(phone, otp);

        Optional<User> optionalUser = userRepository.findByPhone(phone);
        User existingUser = optionalUser.get();
        existingUser.setOtp(otp);
        return userRepository.save(existingUser);
    }

    @Override
    public User addName(String phone, String name) {
        Optional<User> optionalUser = userRepository.findByPhone(phone);
        User user = optionalUser.get();
        user.setName(name);
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User addAddress(String phone, Address address) {
        Optional<User> optionalUser = userRepository.findByPhone(phone);
        User user = optionalUser.get();
        List<Address> addressList = user.getAddress();
        if(addressList == null){
            addressList = new ArrayList<>();
        }
        addressList.add(address);
        user.setAddress(addressList);
        return userRepository.save(user);
    }

    @Override
    public List<Address> getAddress(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        List<Address> addresses = optionalUser.get().getAddress();

        return addresses;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User deleteUser(String id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        User user = optionalUser.get();
        userRepository.delete(user);
        return user;
    }

    @Override
    public User findUserById(String id) throws UserNotVerifiedException {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        if(!user.isVerified()){
            throw new UserNotVerifiedException("User Not Verified ");
        }
        return user;
    }

}
