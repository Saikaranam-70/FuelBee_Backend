package com.FuelBee.backend.repository;

import com.FuelBee.backend.model.Entity.FuelStation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelStationRepository extends MongoRepository<FuelStation, String> {
    Optional<FuelStation> findByLicenseNumber(String licenseNumber);
}
