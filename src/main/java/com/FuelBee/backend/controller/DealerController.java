package com.FuelBee.backend.controller;

import com.FuelBee.backend.service.impl.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dealer")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @PostMapping("/phone-register")
    public ResponseEntity<>
}
