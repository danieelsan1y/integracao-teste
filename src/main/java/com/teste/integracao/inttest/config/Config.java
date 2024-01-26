package com.teste.integracao.inttest.config;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Config {

    public final String VIACEP_HOST;

    public Config(Environment environment) {
        this.VIACEP_HOST = environment.getProperty("VIACEP_HOST");
    }

}
