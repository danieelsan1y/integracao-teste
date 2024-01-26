package com.teste.integracao.inttest.boundaries.impl;

import com.teste.integracao.inttest.boundaries.AddressBoundary;
import com.teste.integracao.inttest.config.Config;
import com.teste.integracao.inttest.domain.Address;
import com.teste.integracao.inttest.dto.AdressDTO;
import com.teste.integracao.inttest.exception.service.ApplicationException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Component
public class AddressBoundaryImpl implements AddressBoundary {

    private final String host;

    @Autowired
    public AddressBoundaryImpl(Environment environment) {
        final Config config = new Config(environment);
        this.host = config.VIACEP_HOST;
    }

    public Address get(String zipCode) {
        if (StringUtils.isBlank(zipCode)) {
            throw new ApplicationException(
                    "Zip code is necessary",
                    BAD_REQUEST
            );
        }

        this.validate();

        final WebTarget webTarget = this.asTarget(String.format(this.host, zipCode));
        final Invocation.Builder invocationBuider = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        final Response response = invocationBuider.get();

        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new ApplicationException(
                    String.format(
                            "Adress [ zipCode: '%s' ] Not Found",
                            zipCode
                    ),
                    NOT_FOUND
            );
        }

        return Optional.ofNullable(response.readEntity(AdressDTO.class))
                .map(Address::new)
                .orElseThrow(() ->
                        new ApplicationException(
                                "Unable to convert to Address",
                                BAD_REQUEST
                        )
                );
    }

    private javax.ws.rs.client.Client newClient() {
        return ClientBuilder.newClient();
    }

    private WebTarget asTarget(String host) {
        return this.newClient().target(host);
    }

    private void validate() {
        if (StringUtils.isNotBlank(this.host)) {
            return;
        }

        throw new ApplicationException(
                "The host environment variable is not configured correctly",
                BAD_REQUEST
        );
    }

}
