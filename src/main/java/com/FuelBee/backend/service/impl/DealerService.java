package com.FuelBee.backend.service.impl;

import com.FuelBee.backend.model.Entity.Dealer;
import com.FuelBee.backend.model.dto.DealerDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DealerService {
    Optional<Dealer> findByPhone(String phone);

    Dealer registerDealer(String phone);

    Dealer loginUser(String phone);

    Optional<Dealer> findDealerByID(String id);

    Dealer addDetails(DealerDTO dealerDTO);

    Dealer getDealerByID(String id);

    void deleteDealerByID(String id);
}
