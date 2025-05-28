package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.exception.UserNotFoundException;
import com.FuelBee.backend.exception.UserNotVerifiedException;
import com.FuelBee.backend.model.Entity.Address;
import com.FuelBee.backend.model.Entity.User;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByPhone(String phoneNumber);



    User saveUser(String phoneNumber);

    User loginUser(String phone);


    User addName(String phone, String name);

    User addAddress(String phone, Address address);

    List<Address> getAddress(String phone);

    Optional<User> findById(String id);

    User deleteUser(String id) throws UserNotFoundException;

    User findUserById(String id) throws UserNotVerifiedException;
}
