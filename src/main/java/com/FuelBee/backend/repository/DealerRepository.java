package com.FuelBee.backend.repository;

import com.FuelBee.backend.model.Entity.Dealer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerRepository extends MongoRepository<Dealer, String> {
    Optional<Dealer> findByPhone(String phone);
}
