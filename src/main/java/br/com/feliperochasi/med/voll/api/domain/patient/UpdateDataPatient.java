package br.com.feliperochasi.med.voll.api.domain.patient;

import br.com.feliperochasi.med.voll.api.domain.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record UpdateDataPatient(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
