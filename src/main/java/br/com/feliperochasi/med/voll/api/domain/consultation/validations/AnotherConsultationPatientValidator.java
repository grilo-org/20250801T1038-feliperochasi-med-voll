package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ConsultationRepository;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;

public class AnotherConsultationPatientValidator {

    private ConsultationRepository repository;

    public void valid(ScheduleConsultationData data) {
        var initDate = data.data().withHour(7);
        var lastDate = data.data().withHour(18);
        var patientHaveAnotherConsultation = repository.existsByPatientIdAndDataBetween(data.idPaciente(), initDate, lastDate);
        if (patientHaveAnotherConsultation) {
            throw new ValidationIdException("Paciente ja possui uma consulta agendada nesse dia");
        }
    }

}
