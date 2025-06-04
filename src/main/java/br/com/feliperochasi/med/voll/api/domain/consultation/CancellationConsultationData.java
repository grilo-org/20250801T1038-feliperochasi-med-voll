package br.com.feliperochasi.med.voll.api.domain.consultation;

import jakarta.validation.constraints.NotNull;

public record CancellationConsultationData(
        @NotNull
        Long idConsulta,
        @NotNull
        ReasonCancel motivo
) {
}
