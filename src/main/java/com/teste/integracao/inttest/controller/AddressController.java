package com.teste.integracao.inttest.controller;

import com.teste.integracao.inttest.domain.Address;
import com.teste.integracao.inttest.exception.service.ApplicationException;
import com.teste.integracao.inttest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{zipCode}")
    ResponseEntity<Address> get(@PathVariable("zipCode") String zipcode) {
        return ResponseEntity.ok(this.addressService.get(zipcode));
    }

}

