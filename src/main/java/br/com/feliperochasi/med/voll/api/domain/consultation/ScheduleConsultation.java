package br.com.feliperochasi.med.voll.api.domain.consultation;

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




        var medic = medicRepository.getReferenceById(data.idMedico());
        var patient = patientRepository.getReferenceById(data.idPaciente());
        var consultation = new Consultation(null, medic, patient, data.data());
        consultationRepository.save(consultation);
    }
}
