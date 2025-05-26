package com.FuelBee.backend.controller;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.PhoneLoginRequest;

import com.FuelBee.backend.service.impl.UserService;
import com.FuelBee.backend.service.impl.UserServiceImpl;
import com.FuelBee.backend.util.OTPUtil;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final OTPUtil otpUtil;

    public UserController(UserServiceImpl userService, OTPUtil otpUtil) {
        this.userService = userService;
        this.otpUtil = otpUtil;
    }

    @PostMapping(value = "/phone-register", consumes = "multipart/form-data")
    public ResponseEntity<PhoneLoginRequest> register(@RequestParam("phone") String phone){
        User user = userService.findByPhone(phone);
        System.out.println("called");
        if (user!=null){
            System.out.println("Error");
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(400));

        }
        User newUser = userService.saveUser(phone);
        return ResponseEntity.status(200).body(new PhoneLoginRequest(newUser.getPhone()));
    }

    @GetMapping("/user")
    public String hello(){
        return "Hello";
    }
}

