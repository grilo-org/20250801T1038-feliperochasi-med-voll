package br.com.feliperochasi.med.voll.api.domain.consultation;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.medic.Medic;
import br.com.feliperochasi.med.voll.api.domain.medic.MedicRepository;
import br.com.feliperochasi.med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleConsultation {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void scheduler(ScheduleConsultationData data) {
        if (!patientRepository.existsById(data.idPaciente())) {
            throw new ValidationIdException("ID do paciente informado nao existe!");
        }

        if (data.idMedico() != null && !medicRepository.existsById(data.idMedico())) {
            throw  new ValidationIdException("ID do medico informado nao existe!");
        }

        var medic = chooseMedic(data);
        var patient = patientRepository.getReferenceById(data.idPaciente());
        var consultation = new Consultation(null, medic, patient, data.data());
        consultationRepository.save(consultation);
    }

    private Medic chooseMedic(ScheduleConsultationData data) {


        return null;
    }
}
