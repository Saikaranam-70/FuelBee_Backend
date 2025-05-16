package com.FuelBee.backend.repository;

import com.FuelBee.backend.model.Entity.User;
import com.FuelBee.backend.model.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<UserDTO> findByEmail(String email);
}
