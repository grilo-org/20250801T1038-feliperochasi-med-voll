package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import br.com.feliperochasi.med.voll.api.domain.consultation.CancellationConsultationData;
import br.com.feliperochasi.med.voll.api.domain.consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TwentyFourHoursBeforeValidator implements CancelConsultationValidator {

    @Autowired
    private ConsultationRepository consultationRepository;

    public void valid(CancellationConsultationData data) {
        var consultation = consultationRepository.getReferenceById(data.idConsulta());
        var now = LocalDateTime.now();
        var twentyFourHoursBefore = consultation.getDate().minusHours(24);

        if (!now.isBefore(twentyFourHoursBefore)) {
            throw new ValidationIdException("O cancelamento de uma consulta deve ser feito 24h antes");
        }
    }
}
