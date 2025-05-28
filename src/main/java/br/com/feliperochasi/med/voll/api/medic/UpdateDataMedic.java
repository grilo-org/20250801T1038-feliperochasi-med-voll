package br.com.feliperochasi.med.voll.api.medic;

import br.com.feliperochasi.med.voll.api.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record UpdateDataMedic(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
