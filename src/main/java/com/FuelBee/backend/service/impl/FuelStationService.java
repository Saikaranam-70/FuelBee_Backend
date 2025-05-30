package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.exception.DuplicateFuelTypeException;
import com.FuelBee.backend.exception.FuelNotFoundException;
import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.Entity.FuelInfo;
import com.FuelBee.backend.model.Entity.FuelStation;
import com.FuelBee.backend.model.dto.FuelInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface FuelStationService {
    Optional<Dealer> findDealerById(String dealerId);

    Optional<FuelStation> findFuelStationByLicenseNumber(String licenseNumber);

    FuelStation addFuelStation(String dealerId, FuelStation fuelStation, MultipartFile image);

    Optional<FuelStation> findFuelStationById(String stationId);

    FuelStation addFuelInfo(FuelInfo fuelInfo, String stationId) throws DuplicateFuelTypeException;

    FuelStation updateFuels(FuelInfoDto fuelInfoDto) throws FuelNotFoundException;
}
