package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;

public interface SchedulingConsultationValidator {
    void valid(ScheduleConsultationData data);
}
