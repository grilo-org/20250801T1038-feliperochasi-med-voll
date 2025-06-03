package br.com.feliperochasi.med.voll.api.domain.medic;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findAllByActiveTrue(Pageable p);

    @Query("""
            SELECT m FROM Medic m
            WHERE m.active = true AND m.specialised = :specialised AND m.id not in(SELECT c.medic.id FROM Consultation WHERE c.date = :data) ORDER BY rand() LIMIT 1
            """)
    Medic chooseMedicBySpecialised(Specialised specialised, @NotNull @Future LocalDateTime data);
}
