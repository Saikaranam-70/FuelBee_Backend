package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.UserDTO;
import com.FuelBee.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final imageUploadService imageUploadService;
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(com.FuelBee.backend.service.impl.imageUploadService imageUploadService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.imageUploadService = imageUploadService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(UserDTO dto, MultipartFile imageFile) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setAddress(dto.getAddress());

        if (imageFile !=null && !imageFile.isEmpty()){
            String imgUrl = null;
            try {
                imgUrl = imageUploadService.uploadImage(imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            user.setProfileImageUrl(imgUrl);
        }
        return null;
    }
}
