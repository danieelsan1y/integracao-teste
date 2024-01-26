package com.teste.integracao.inttest.boundaries;

import com.teste.integracao.inttest.domain.Address;

public interface AddressBoundary {

    Address get (String zipcode);

}
