package com.teste.integracao.inttest.exception.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationError {

    private final String status;
    private final String message;

}
