package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.patient.*;
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
        return repository.findAllByActiveTrue(p).map(ListDataPatient::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDataPatient updateDataPatient) {
        var patient = repository.getReferenceById(updateDataPatient.id());
        patient.update(updateDataPatient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.exclude();
    }
}
