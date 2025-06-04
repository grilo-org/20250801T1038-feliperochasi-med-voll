package br.com.feliperochasi.med.voll.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByMedicIdAndDateTime(Long idMedic, LocalDateTime date);

    Boolean existsByPatientIdAndDateTimeBetween(Long idPatient, LocalDateTime initDate, LocalDateTime lastDate);
}
