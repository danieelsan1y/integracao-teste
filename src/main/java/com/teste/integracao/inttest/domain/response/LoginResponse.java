package com.teste.integracao.inttest.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    private String email;
    private String token;
}
