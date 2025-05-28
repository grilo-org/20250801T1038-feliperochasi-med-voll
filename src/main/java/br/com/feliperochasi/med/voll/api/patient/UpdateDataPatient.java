package br.com.feliperochasi.med.voll.api.patient;

import br.com.feliperochasi.med.voll.api.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record UpdateDataPatient(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
