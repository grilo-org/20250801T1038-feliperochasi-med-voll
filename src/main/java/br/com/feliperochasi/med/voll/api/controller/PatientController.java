package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.patient.ListDataPatient;
import br.com.feliperochasi.med.voll.api.patient.Patient;
import br.com.feliperochasi.med.voll.api.patient.PatientRepository;
import br.com.feliperochasi.med.voll.api.patient.RegisterDataPatient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDataPatient registerDataPatient) {
        repository.save(new Patient(registerDataPatient));
    }

    @GetMapping
    public Page<ListDataPatient> listPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable p) {
        return repository.findAll(p).map(ListDataPatient::new);
    }
}
