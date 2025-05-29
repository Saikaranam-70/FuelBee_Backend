package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.dto.DealerDTO;
import com.FuelBee.backend.repository.DealerRepository;
import com.FuelBee.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService{
    @Autowired
    private OtpService otpService;
    @Autowired
    private DealerRepository dealerRepository;

    @Override
    public Optional<Dealer> findByPhone(String phone) {
        return dealerRepository.findByPhone(phone);
    }

    @Override
    public Dealer registerDealer(String phone) {
        String otp = otpService.generateOtp();
        otpService.sendDealerPhoneOtp(phone, otp);
        Dealer dealer = new Dealer();
        dealer.setPhone(phone);
        dealer.setOtp(otp);

        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer loginUser(String phone) {
        String otp = otpService.generateOtp();
        otpService.sendDealerPhoneOtp(phone, otp);

        Optional<Dealer> optionalDealer= dealerRepository.findByPhone(phone);
        Dealer dealer= optionalDealer.get();
        dealer.setPhone(phone);
        dealer.setOtp(otp);

        return dealerRepository.save(dealer);
    }

    @Override
    public Optional<Dealer> findDealerByID(String id) {
        return dealerRepository.findById(id);
    }

    @Override
    public Dealer addDetails(DealerDTO dealerDTO) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(dealerDTO.getId());
        Dealer dealer = optionalDealer.get();
        dealer.setName(dealerDTO.getName());
        dealer.setCompanyName(dealerDTO.getCompanyName());
        dealer.setGstNumber(dealerDTO.getGstNumber());

        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer getDealerByID(String id) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(id);
        Dealer dealer = optionalDealer.get();

        Dealer showDealer = new Dealer();
        showDealer.setId(dealer.getId());
        showDealer.setName(dealer.getName());
        showDealer.setEmail(dealer.getEmail());
        showDealer.setPhone(dealer.getPhone());
        showDealer.setCompanyName(dealer.getCompanyName());
        showDealer.setGstNumber(dealer.getGstNumber());
        showDealer.setStatus(dealer.getStatus());
        showDealer.setActive(dealer.isActive());
        showDealer.setVerified(dealer.isVerified());
        showDealer.setFuelStations(dealer.getFuelStations());
        return showDealer;
    }

    @Override
    public void deleteDealerByID(String id) {
        dealerRepository.deleteById(id);
    }
}
