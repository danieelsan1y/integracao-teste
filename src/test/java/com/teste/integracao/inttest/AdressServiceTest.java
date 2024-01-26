package com.teste.integracao.inttest;

import com.teste.integracao.inttest.boundaries.AddressBoundary;
import com.teste.integracao.inttest.domain.Address;
import com.teste.integracao.inttest.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class AdressServiceTest {

    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    AddressBoundary addressBoundary;

    Address address;

    @BeforeEach
    public void init() {
        this.address = new Address(
                "75115-640",
                "Avenida Paranapanema",
                "",
                "Jardim América",
                "Anápolis",
                "GO",
                "5201108",
                "",
                "62",
                "9221"
        );
    }

    @Test
    public void shouldReturnTheExpectedObject() {

        Mockito.when(this.addressBoundary.get(eq("75115640")))
                .thenReturn(this.address);

        Assertions.assertEquals(this.address, this.addressService.get("75115640"));
    }

    @Test
    public void shouldReturnAnErrorWhenNotFindingTheAddressByZipCode() {

        Mockito.when(this.addressBoundary.get(eq("0000000")))
                .thenThrow(new RuntimeException("Adress [ zipCode '0000000' ] Not Found"));

        Assertions.assertThrows(RuntimeException.class, () -> this.addressService.get("0000000"));
    }

    @Test
    public void shouldReturnAnErrorWhenUnableToConvertTheRequestToAddress() {

        Mockito.when(this.addressBoundary.get(eq("75115640")))
                .thenThrow(new RuntimeException("Unable to convert to Address"));

        Assertions.assertThrows(RuntimeException.class, () -> this.addressService.get("75115640"));
    }
}

