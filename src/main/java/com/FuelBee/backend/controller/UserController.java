package com.FuelBee.backend.controller;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.UserDTO;
import com.FuelBee.backend.service.impl.UserService;
import com.FuelBee.backend.service.impl.UserServiceImpl;
import com.FuelBee.backend.util.OTPUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto, @RequestBody MultipartFile imageFile){
        Optional<UserDTO> optionalUserDTO = userService.findByEmail(dto.getEmail());
        if (!optionalUserDTO.isEmpty()){
            return ResponseEntity.status(400).body("User with details Already Exists");
        }
        User user = userService.createUser(dto, imageFile);
        otpUtil.generateAndSendOTP(user);
        return ResponseEntity.status(200).body("User registered Successfully. OTP sent.");
    }
}
