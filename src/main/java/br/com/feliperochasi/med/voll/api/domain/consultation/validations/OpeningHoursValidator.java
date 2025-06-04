package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHoursValidator implements SchedulingConsultationValidator {

    public void valid(ScheduleConsultationData data) {
        var consultationData = data.data();
        var sunday = consultationData.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = consultationData.getHour() < 7;
        var afterClose = consultationData.getHour() > 18;
        if (sunday || beforeOpening || afterClose) {
            throw new ValidationIdException("Consulta fora do horario de funcionamento da clinica");
        }
    }
}
