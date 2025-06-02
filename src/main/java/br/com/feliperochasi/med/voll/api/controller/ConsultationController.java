package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.consultation.DetailsConsultationData;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultation;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultationController {

    @Autowired
    private ScheduleConsultation service;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleConsultationData data) {
        service.scheduler(data);
        return ResponseEntity.ok(new DetailsConsultationData(null, null, null, null));
    }
}
