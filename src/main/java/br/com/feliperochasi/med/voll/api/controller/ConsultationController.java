package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.consultation.DetailsConsultationData;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultationController {

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleConsultationData data) {
        return ResponseEntity.ok(new DetailsConsultationData(null, null, null, null));
    }
}
