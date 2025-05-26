package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.User;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface UserService {

    User findByPhone(String phoneNumber);



    User saveUser(String phoneNumber);
}
