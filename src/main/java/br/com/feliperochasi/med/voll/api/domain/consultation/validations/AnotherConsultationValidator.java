package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ConsultationRepository;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;

public class AnotherConsultationValidator {

    private ConsultationRepository repository;

    public void valid(ScheduleConsultationData data) {
        var medicHaveConsultation = repository.existsByMedicIdAndDate(data.idMedico(), data.data());
        if (medicHaveConsultation) {
            throw new ValidationIdException("Medico ja possui outra consulta agendada nesse mesmo horario");
        }
    }
}
