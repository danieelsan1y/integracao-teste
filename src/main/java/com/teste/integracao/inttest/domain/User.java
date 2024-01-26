package com.teste.integracao.inttest.domain;

import lombok.Getter;
import lombok.Setter;

import static java.util.UUID.randomUUID;

@Getter
@Setter
public class User implements DomainEntity{

    private final String id = randomUUID().toString();
    private final String email;
    private final String password;
    private String firstName;
    private String lastName;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
