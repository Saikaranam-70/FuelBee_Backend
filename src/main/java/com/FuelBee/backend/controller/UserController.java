package com.FuelBee.backend.controller;

import com.FuelBee.backend.exception.AddressNotFoundException;
import com.FuelBee.backend.exception.UserNotFoundException;
import com.FuelBee.backend.exception.UserNotVerifiedException;
import com.FuelBee.backend.model.Entity.Address;
import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.AddAddressRequest;
import com.FuelBee.backend.model.dto.PhoneLoginRequest;

import com.FuelBee.backend.response.UserResponse;
import com.FuelBee.backend.response.otpResponse;
import com.FuelBee.backend.service.impl.OtpService;
import com.FuelBee.backend.service.impl.UserService;
import com.FuelBee.backend.service.impl.UserServiceImpl;
import com.FuelBee.backend.util.OTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final OTPUtil otpUtil;
    @Autowired
    private OtpService otpService;

    public UserController(UserServiceImpl userService, OTPUtil otpUtil) {
        this.userService = userService;
        this.otpUtil = otpUtil;
    }

    @PostMapping(value = "/phone-register", consumes = "multipart/form-data")
    public ResponseEntity<PhoneLoginRequest> register(@RequestParam("phone") String phone){
        try{
            Optional<User> optionalUser = userService.findByPhone(phone);
            if (!optionalUser.isPresent()){
                User newUser = userService.saveUser(phone);
                return ResponseEntity.status(200).body(new PhoneLoginRequest(newUser.getPhone()));
            }else{
                User loginUser = userService.loginUser(phone);
                return ResponseEntity.status(200).body(new PhoneLoginRequest(loginUser.getPhone()));
            }
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error :"+ e.getMessage());
        }
    }

    @PostMapping("/otp-validation")
    public ResponseEntity<otpResponse> otp_validation(@RequestParam("phone") String phone, @RequestParam("otp") String otp){
        try{
            Optional<User> optionalUser = userService.findByPhone(phone);
            if (!optionalUser.isPresent()){
                throw new UserNotFoundException("User Not Found");
            }
            Boolean isValid = otpService.validatePhoneOtp(phone, otp);
            System.out.println(isValid);
            System.out.println("Called");
            if(isValid){
                return new ResponseEntity<>(new otpResponse("OTP Verified Successfully", HttpStatus.OK.value(), optionalUser.get().getPhone()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new otpResponse("Invalid OTP", HttpStatus.BAD_REQUEST.value(), null), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception | UserNotFoundException e){
            throw new RuntimeException("Internal Server Error||User not Found");
        }
    }

    @PutMapping("/add-name")
    public ResponseEntity<User> addNameToUser(@RequestParam("phone") String phone, @RequestParam("name") String name)throws UserNotFoundException{
        try{
            Optional<User> optionalUser = userService.findByPhone(phone);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User Not Found");
            }
            User user = userService.addName(phone, name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error :"+ e.getMessage());
        }
    }

    @PutMapping("/add-address")
    public ResponseEntity<User> addAddress(@RequestBody AddAddressRequest request) throws UserNotFoundException{
        try{
            String phone = request.getPhone();
            Address address = request.getAddress();
            Optional<User> optionalUser = userService.findByPhone(phone);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User Not Found");
            }
            User user = userService.addAddress(phone, address);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            throw  new RuntimeException("Internal Server Error :"+e.getMessage());
        }
    }

    @GetMapping("/get-address/{id}")
    public ResponseEntity<List<Address>> getAddress(@PathVariable("id") String id)throws UserNotFoundException, AddressNotFoundException{
        try{
            Optional<User> optionalUser = userService.findById(id);
            if(!optionalUser.isPresent()){
                System.out.println("Called");
                throw new UserNotFoundException("User Not Found");
            }
            List<Address> address = userService.getAddress(id);
            System.out.println(address);
            System.out.println("called");
            if(address.isEmpty()){
                System.out.println("Called it");
                throw new AddressNotFoundException("Address Not Found");
            }
            return new ResponseEntity<>(address, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error :"+e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("id") String id) throws UserNotFoundException{
        Optional<User> optionalUser = userService.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(new UserResponse("User Deleted Successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) throws UserNotFoundException{
        try{
            Optional<User> optionalUser = userService.findById(id);
            if (!optionalUser.isPresent()){
                throw new UserNotFoundException("User Not Found");
            }
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotVerifiedException e) {
            throw new RuntimeException(e);
        }
    }
}

