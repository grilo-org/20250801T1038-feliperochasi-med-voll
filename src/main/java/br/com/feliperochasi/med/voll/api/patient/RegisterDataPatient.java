package br.com.feliperochasi.med.voll.api.patient;

import br.com.feliperochasi.med.voll.api.address.Address;
import br.com.feliperochasi.med.voll.api.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDataPatient(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @Valid
        @NotNull
        DataAddress endereco) {
}
