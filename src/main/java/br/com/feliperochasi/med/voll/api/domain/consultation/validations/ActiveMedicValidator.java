package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import br.com.feliperochasi.med.voll.api.domain.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMedicValidator implements SchedulingConsultationValidator {

    @Autowired
    private MedicRepository repository;

    public void valid(ScheduleConsultationData data) {
        if (data.idMedico() == null) {
            return;
        }

        var isActiveMedic = repository.findActiveById(data.idMedico());
        if (!isActiveMedic) {
            throw new ValidationIdException("Consulta nao pode ser agendada com medico excluido");
        }
    }
}
