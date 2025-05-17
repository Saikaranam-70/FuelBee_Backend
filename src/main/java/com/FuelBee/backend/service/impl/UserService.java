package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface UserService {
    Optional<UserDTO> findByEmail(String email);

    User createUser(UserDTO dto, MultipartFile imageFile);
}
