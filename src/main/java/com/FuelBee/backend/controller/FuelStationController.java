package com.FuelBee.backend.controller;

import com.FuelBee.backend.exception.DealerNotFoundException;
import com.FuelBee.backend.exception.FuelStationAlreadyExistException;
import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.Entity.FuelStation;
import com.FuelBee.backend.model.dto.FuelStationDto;
import com.FuelBee.backend.response.FuelStationResponse;
import com.FuelBee.backend.service.impl.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/fuel-station")
public class FuelStationController {

    @Autowired
    private FuelStationService fuelStationService;

    @PostMapping("/add")
    public ResponseEntity<FuelStationResponse> addFuelStation(@ModelAttribute FuelStationDto fuelStationDto,@RequestParam("image") MultipartFile image){
        try{
            String dealerId = fuelStationDto.getDealerId();
            FuelStation fuelStation = fuelStationDto.getFuelStation();

            Optional<Dealer> optionalDealer = fuelStationService.findDealerById(dealerId);
            Optional<FuelStation> optionalFuelStation = fuelStationService.findFuelStationByLicenseNumber(fuelStation.getLicenseNumber());
            if(!optionalDealer.isPresent()){
                throw new DealerNotFoundException("Dealer Not Found");
            }
            if(!optionalFuelStation.isEmpty()){
                throw new FuelStationAlreadyExistException("Fuel Station Already Exists");
            }

            FuelStation fuelStation1 = fuelStationService.addFuelStation(dealerId, fuelStation, image);
            return new ResponseEntity<>(new FuelStationResponse("Fuel Station Added Successfully", fuelStation1.getId(), HttpStatus.OK.value()), HttpStatus.OK);

        } catch (DealerNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FuelStationAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }
}
