package com.teste.integracao.inttest.service.impl;

import com.teste.integracao.inttest.boundaries.AddressBoundary;
import com.teste.integracao.inttest.domain.Address;
import com.teste.integracao.inttest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressBoundary addressBoundary;

    @Autowired
    public AddressServiceImpl(AddressBoundary addressBoundary) {
        this.addressBoundary = addressBoundary;
    }

    public Address get(String zipCode) {
        return addressBoundary.get(zipCode);
    }

}
