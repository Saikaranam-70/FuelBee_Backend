package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.Entity.FuelStation;
import com.FuelBee.backend.repository.DealerRepository;
import com.FuelBee.backend.repository.FuelStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelStationServiceImpl implements FuelStationService{

    @Autowired
    private FuelStationRepository fuelStationRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private imageUploadService imageUploadService;
    @Override
    public Optional<Dealer> findDealerById(String dealerId) {
        return dealerRepository.findById(dealerId);
    }

    @Override
    public Optional<FuelStation> findFuelStationByLicenseNumber(String licenseNumber) {
        return fuelStationRepository.findByLicenseNumber(licenseNumber);
    }

    @Override
    public FuelStation addFuelStation(String dealerId, FuelStation fuelStation, MultipartFile image) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(dealerId);
        Dealer dealer = optionalDealer.get();
        FuelStation fuelStation1 = new FuelStation();
        fuelStation1.setStationName(fuelStation.getStationName());
        fuelStation1.setLicenseNumber(fuelStation.getLicenseNumber());
        fuelStation1.setContactNumber(fuelStation.getContactNumber());
        if(image!=null && !image.isEmpty()){
            try {
                String imageUrl = imageUploadService.uploadImage(image);
                fuelStation1.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        fuelStation1.setDealer(dealer);

        List<FuelStation> fuelStations = dealer.getFuelStations();
        if(fuelStations == null){
            fuelStations = new ArrayList<>();
        }
        fuelStations.add(fuelStation1);
        dealer.setFuelStations(fuelStations);
        dealerRepository.save(dealer);

        return fuelStationRepository.save(fuelStation1);
    }
}
