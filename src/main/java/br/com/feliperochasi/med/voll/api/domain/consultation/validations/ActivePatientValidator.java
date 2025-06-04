package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import br.com.feliperochasi.med.voll.api.domain.patient.PatientRepository;

public class ActivePatientValidator {

    private PatientRepository repository;

    public void valid(ScheduleConsultationData data) {
        var isActivePatient = repository.findActiveById(data.idPaciente());
        if (!isActivePatient) {
            throw new ValidationIdException("Paciente nao esta ativo");
        }
    }
}
