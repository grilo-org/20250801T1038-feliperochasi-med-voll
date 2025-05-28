package br.com.feliperochasi.med.voll.api.domain.medic;

import br.com.feliperochasi.med.voll.api.domain.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record UpdateDataMedic(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
