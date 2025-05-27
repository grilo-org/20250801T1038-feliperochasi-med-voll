package br.com.feliperochasi.med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;
    private String neighborhood;
    private String zipcode;
    private String number;
    private String complement;
    private String city;
    private String uf;

    public Address(DataAddress data) {
        this.address = data.logradouro();
        this.neighborhood = data.bairro();
        this.zipcode = data.cep();
        this.number = data.numero();
        this.city = data.cidade();
        this.uf = data.uf();
        this.complement = data.complemento();
    }
}
