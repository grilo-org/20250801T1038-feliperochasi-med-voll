package br.com.feliperochasi.med.voll.api.domain.consultation.validations;

import br.com.feliperochasi.med.voll.api.domain.consultation.CancellationConsultationData;

public interface CancelConsultationValidator {
    void valid(CancellationConsultationData data);
}
