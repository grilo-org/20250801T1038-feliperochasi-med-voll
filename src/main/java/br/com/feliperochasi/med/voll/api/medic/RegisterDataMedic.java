package br.com.feliperochasi.med.voll.api.medic;

import br.com.feliperochasi.med.voll.api.address.DataAddress;

public record RegisterDataMedic(String nome,
                                String email,
                                String crm,
                                Specialised especialidade,
                                String telefone,
                                DataAddress endereco) {
}
