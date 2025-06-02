package br.com.feliperochasi.med.voll.api.domain.medic;

import br.com.feliperochasi.med.voll.api.domain.address.Address;

public record DetailsDataMedic(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Specialised especialidade,
        Address endereco) {

    public DetailsDataMedic(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getPhone(), medic.getSpecialised(), medic.getAddress());
    }
}
