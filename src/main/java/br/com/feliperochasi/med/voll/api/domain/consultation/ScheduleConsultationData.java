package br.com.feliperochasi.med.voll.api.domain.consultation;

import br.com.feliperochasi.med.voll.api.domain.medic.Specialised;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleConsultationData(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Specialised especialidade
) {
}
