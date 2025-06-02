package br.com.feliperochasi.med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleConsultationData(
        String idMedico,

        @NotNull
        String idPaciente,

        @NotNull
        @Future
        LocalDateTime data
) {
}
