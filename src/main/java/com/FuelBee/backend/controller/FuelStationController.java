package com.FuelBee.backend.controller;

import com.FuelBee.backend.exception.*;
import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.Entity.FuelInfo;
import com.FuelBee.backend.model.Entity.FuelStation;
import com.FuelBee.backend.model.dto.FuelInfoDto;
import com.FuelBee.backend.model.dto.FuelStationDto;
import com.FuelBee.backend.response.FuelStationResponse;
import com.FuelBee.backend.service.impl.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
            FuelStation fuelStation = new FuelStation();
            fuelStation.setStationName(fuelStationDto.getStationName());
            fuelStation.setLicenseNumber(fuelStationDto.getLicenseNumber());
            fuelStation.setContactNumber(fuelStationDto.getContactNumber());

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


    @PutMapping("/fuelInfo")
    public ResponseEntity<FuelStationResponse> addFuelInfo(@RequestBody FuelInfoDto fuelInfoDto){
        try{
            String stationId = fuelInfoDto.getFuelStationId();
            FuelInfo fuelInfo = fuelInfoDto.getFuelInfo();

            Optional<FuelStation> optionalFuelStation = fuelStationService.findFuelStationById(stationId);
            if(optionalFuelStation.isEmpty()){
                throw new FuelStationNotFoundException("Fuel Station Not Found");
            }
            FuelStation fuelStation = fuelStationService.addFuelInfo(fuelInfo, stationId);
            return new ResponseEntity<>(new FuelStationResponse("Fuel Info Added Successfully", fuelStation.getId(),HttpStatus.OK.value()), HttpStatus.OK);
        } catch (FuelStationNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DuplicateFuelTypeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<FuelStationResponse> updateFuelStation(@RequestBody FuelInfoDto fuelInfoDto) throws FuelStationNotFoundException, FuelNotFoundException {
        Optional<FuelStation> optionalFuelStation = fuelStationService.findFuelStationById(fuelInfoDto.getFuelStationId());
        if(optionalFuelStation.isEmpty()){
            throw new FuelStationNotFoundException("Fuel Station Not Found");
        }
        FuelStation fuelStation = fuelStationService.updateFuels(fuelInfoDto);
        return new ResponseEntity<>(new FuelStationResponse("Fuel Updated Successfully", fuelStation.getId(), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/fuelStation")
    public ResponseEntity<List<FuelStation>> getAllFuelStations() throws FuelStationNotFoundException {
        List<FuelStation> optionalFuelStations = fuelStationService.findAllFuelStations();
        if(optionalFuelStations.isEmpty()){
            throw new FuelStationNotFoundException("Fuel Stations Not Found");
        }
        return new ResponseEntity<>(optionalFuelStations, HttpStatus.OK);
    }

    @GetMapping("/get-fuel/{type}")
    public ResponseEntity<FuelStation> getFuelByType(@PathVariable("type") String Type){
        return null;
    }

}
