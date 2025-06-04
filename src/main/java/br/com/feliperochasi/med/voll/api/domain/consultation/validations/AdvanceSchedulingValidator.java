package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceSchedulingValidator implements SchedulingConsultationValidator {

    public void valid(ScheduleConsultationData data) {
        var consultationData = data.data();
        var now = LocalDateTime.now();
        var diff = Duration.between(now, consultationData).toMinutes();

        if (diff < 30) {
            throw new ValidationIdException("Consulta deve ser agendada com antecedencia minima de 30 minutos");
        }
    }
}
