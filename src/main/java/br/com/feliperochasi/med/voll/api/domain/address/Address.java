package br.com.feliperochasi.med.voll.api.domain.address;

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

    public void update(DataAddress dataAddress) {
        if(dataAddress.logradouro() != null) {
            this.address = dataAddress.logradouro();
        }
        if(dataAddress.bairro() != null) {
            this.neighborhood = dataAddress.bairro();
        }
        if(dataAddress.cep() != null) {
            this.zipcode = dataAddress.cep();
        }
        if(dataAddress.numero() != null) {
            this.number = dataAddress.numero();
        }
        if(dataAddress.cidade() != null) {
            this.city = dataAddress.cidade();
        }
        if(dataAddress.uf() != null) {
            this.uf = dataAddress.uf();
        }
        if(dataAddress.complemento() != null) {
            this.complement = dataAddress.complemento();
        }

    }
}
