package br.com.feliperochasi.med.voll.api.domain.consultation;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByMedicIdAndDate(Long idMedic, LocalDateTime date);

    Boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime initDate, LocalDateTime lastDate);
}
