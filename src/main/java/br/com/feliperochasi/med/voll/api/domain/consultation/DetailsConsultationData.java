package br.com.feliperochasi.med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record DetailsConsultationData(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
}
