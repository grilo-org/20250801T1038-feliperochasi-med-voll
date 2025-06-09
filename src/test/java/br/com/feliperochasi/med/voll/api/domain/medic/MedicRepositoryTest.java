package br.com.feliperochasi.med.voll.api.domain.medic;

import br.com.feliperochasi.med.voll.api.domain.address.DataAddress;
import br.com.feliperochasi.med.voll.api.domain.consultation.Consultation;
import br.com.feliperochasi.med.voll.api.domain.patient.Patient;
import br.com.feliperochasi.med.voll.api.domain.patient.RegisterDataPatient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void chooseMedicBySpecialisedFirst() {
        //given ou arrange
        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medic = registerMedic("Medico", "medico@voll.med", "123456", Specialised.ORTOPEDIA);
        var patient = registerPatient("Paciente", "paciente@voll.med", "00000000000");
        registerConsultation(medic, patient, nextMonday);

        //when ou act
        var medicAvailable = medicRepository.chooseMedicBySpecialised(Specialised.ORTOPEDIA, nextMonday);

        //then ou assert
        assertThat(medicAvailable).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void chooseMedicBySpecialisedSecond() {

        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medic = registerMedic("Medico", "medico@voll.med", "123456", Specialised.ORTOPEDIA);

        var medicAvailable = medicRepository.chooseMedicBySpecialised(Specialised.ORTOPEDIA, nextMonday);
        assertThat(medicAvailable).isEqualTo(medic);
    }

    private void registerConsultation(Medic medic, Patient patient, LocalDateTime date) {
        em.persist(new Consultation(null, medic, patient, date));
    }

    private Medic registerMedic(String name, String email, String crm, Specialised specialised) {
        var medic = new Medic(dataMedic(name, email, crm, specialised));
        em.persist(medic);
        return medic;

    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(dataPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private RegisterDataMedic dataMedic(String name, String email, String crm, Specialised specialised)  {
        return new RegisterDataMedic(
                name,
                email,
                crm,
                specialised,
                "61999999999",
                dataAddress()
        );
    }

    private RegisterDataPatient dataPatient(String name, String email, String cpf) {
        return new RegisterDataPatient(
                name,
                email,
                "61999999999",
                cpf,
                dataAddress()
        );
    }

    private DataAddress dataAddress() {
        return new DataAddress(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}