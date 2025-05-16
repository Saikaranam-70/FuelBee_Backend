package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.UserDTO;
import com.FuelBee.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(UserDTO dto) {
        User user = new User();
        return null;
    }
}
