package com.teste.integracao.inttest.service;

import com.teste.integracao.inttest.domain.Address;

public interface AddressService {

    Address get(String zipCode);

}
