package com.FuelBee.backend.controller;

import com.FuelBee.backend.exception.DealerNotFoundException;
import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.dto.DealerDTO;
import com.FuelBee.backend.response.DealerResponse;
import com.FuelBee.backend.service.impl.DealerService;
import com.FuelBee.backend.service.impl.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dealer")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private OtpService otpService;

    @PostMapping("/phone-register")
    public ResponseEntity<DealerResponse> registerUser(@RequestParam("phone") String phone){
        try{
            Optional<Dealer> optionalDealer = dealerService.findByPhone(phone);
            Dealer dealer;
            if(optionalDealer.isEmpty()){
                dealer = dealerService.registerDealer(phone);
                return new ResponseEntity<>(new DealerResponse("Registered Successfully", dealer.getId(), HttpStatus.OK.value()), HttpStatus.OK);
            }else{
                dealer = dealerService.loginUser(phone);
                return new ResponseEntity<>(new DealerResponse("Login Successfully", dealer.getId(), HttpStatus.OK.value()), HttpStatus.OK);
            }
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PostMapping("/otp-validation")
    public  ResponseEntity<DealerResponse> otp_validation(@RequestParam("phone") String phone, @RequestParam("otp") String otp) throws DealerNotFoundException{
        try{
            Optional<Dealer> optionalDealer = dealerService.findByPhone(phone);
            if(!optionalDealer.isPresent()){
                throw new DealerNotFoundException("Dealer Not Found");
            }
            Boolean isValid = otpService.validateDealerPhoneOtp(phone, otp);
            if (isValid){
                return new ResponseEntity<>(new DealerResponse("OTP Verified Successfully", optionalDealer.get().getId(), HttpStatus.OK.value()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new DealerResponse("Invalid OTP", null, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new RuntimeException("INTERNAL SERVER ERROR"+ e.getMessage());
        }
    }

    @PutMapping("/add-details")
    public ResponseEntity<Dealer> addDetails(@RequestBody DealerDTO dealerDTO) {
        try{
            Optional<Dealer> optionalDealer = dealerService.findDealerByID(dealerDTO.getId());
            if(optionalDealer.isEmpty()){
                throw new DealerNotFoundException("Dealer Not Found");
            }
            Dealer dealer = dealerService.addDetails(dealerDTO);
            return new ResponseEntity<>(dealer, HttpStatus.OK);
        } catch (DealerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
