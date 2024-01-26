package com.teste.integracao.inttest.exception.advice;

import com.teste.integracao.inttest.exception.service.ApplicationException;
import com.teste.integracao.inttest.exception.service.ApplicationError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceApi {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationError> resourceError(ApplicationException e) {
        return ResponseEntity.status(e.getStatus()).
                body(
                        new ApplicationError(
                                String.valueOf(e.getStatus()),
                                e.getMessage()
                        )
                );
    }

}
