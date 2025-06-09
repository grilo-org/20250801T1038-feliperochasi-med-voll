package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import br.com.feliperochasi.med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements SchedulingConsultationValidator {

    @Autowired
    private PatientRepository repository;

    public void valid(ScheduleConsultationData data) {
        var isActivePatient = repository.findActiveById(data.idPaciente());
        if (!isActivePatient) {
            throw new ValidationIdException("Paciente nao esta ativo");
        }
    }
}
