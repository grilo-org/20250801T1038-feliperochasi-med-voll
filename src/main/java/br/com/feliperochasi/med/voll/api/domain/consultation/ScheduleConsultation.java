package br.com.feliperochasi.med.voll.api.domain.consultation;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.validations.CancelConsultationValidator;
import br.com.feliperochasi.med.voll.api.domain.consultation.validations.SchedulingConsultationValidator;
import br.com.feliperochasi.med.voll.api.domain.medic.Medic;
import br.com.feliperochasi.med.voll.api.domain.medic.MedicRepository;
import br.com.feliperochasi.med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleConsultation {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<SchedulingConsultationValidator> validators;

    @Autowired
    private List<CancelConsultationValidator> cancelConsultationValidators;

    public DetailsConsultationData scheduler(ScheduleConsultationData data) {
        if (!patientRepository.existsById(data.idPaciente())) {
            throw new ValidationIdException("ID do paciente informado nao existe!");
        }

        if (data.idMedico() != null && !medicRepository.existsById(data.idMedico())) {
            throw  new ValidationIdException("ID do medico informado nao existe!");
        }

        validators.forEach(v -> v.valid(data));

        var medic = chooseMedic(data);
        if (medic == null) {
            throw  new ValidationIdException("Nao existe medico disponivel nesta data!");
        }
        var patient = patientRepository.getReferenceById(data.idPaciente());
        var consultation = new Consultation(null, medic, patient, data.data(), true, null);
        consultationRepository.save(consultation);
        return new DetailsConsultationData(consultation);
    }

    public void cancel(CancellationConsultationData data) {
        if (!consultationRepository.existsById(data.idConsulta())) {
            throw new ValidationIdException("ID da consulta informado nao existe");
        }

        cancelConsultationValidators.forEach(v -> v.valid(data));
        var consultation = consultationRepository.getReferenceById(data.idConsulta());
        consultation.delete();
    }

    private Medic chooseMedic(ScheduleConsultationData data) {
        if(data.idMedico() != null) {
            return medicRepository.getReferenceById(data.idMedico());
        }

        if(data.especialidade() == null) {
            throw new ValidationIdException("Especialidade é obrigatoria quando medico nao for escolhido!");
        }

        return medicRepository.chooseMedicBySpecialised(data.especialidade(), data.data());
    }

}
