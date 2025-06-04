package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.consultation.CancellationConsultationData;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultation;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultationController {

    @Autowired
    private ScheduleConsultation service;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleConsultationData data) {
        var dto = service.scheduler(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid CancellationConsultationData data) {
        service.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
