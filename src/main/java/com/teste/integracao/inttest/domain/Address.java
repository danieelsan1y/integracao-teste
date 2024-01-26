package com.teste.integracao.inttest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.integracao.inttest.dto.AdressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements DomainEntity {

    private final String id = randomUUID().toString();

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("logradouro")
    private String publicPlace;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String district;

    @JsonProperty("localidade")
    private String locality;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("ibge")
    private String ibgeCode;

    @JsonProperty("gia")
    private String giaCode;

    @JsonProperty("ddd")
    private String areaCode;

    @JsonProperty("siafi")
    private String siafiCode;

    public Address(AdressDTO dto) {
        this.zipCode = dto.getCep();
        this.publicPlace = dto.getLogradouro();
        this.complement = dto.getComplemento();
        this.district = dto.getBairro();
        this.locality = dto.getLocalidade();
        this.uf = dto.getUf();
        this.ibgeCode = dto.getIbge();
        this.giaCode = dto.getGia();
        this.areaCode = dto.getDdd();
        this.siafiCode = dto.getSiafi();
    }

}
