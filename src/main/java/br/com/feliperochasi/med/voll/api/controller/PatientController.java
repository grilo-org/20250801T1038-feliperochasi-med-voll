package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.patient.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsDataPatient> register(@RequestBody @Valid RegisterDataPatient registerDataPatient, UriComponentsBuilder uriBuilder) {
        var pantient = new Patient(registerDataPatient);
        repository.save(pantient);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pantient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsDataPatient(pantient));
    }

    @GetMapping
    public ResponseEntity<Page<ListDataPatient>> listPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable p) {
        var page = repository.findAllByActiveTrue(p).map(ListDataPatient::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailsDataPatient> update(@RequestBody @Valid UpdateDataPatient updateDataPatient) {
        var patient = repository.getReferenceById(updateDataPatient.id());
        patient.update(updateDataPatient);
        return ResponseEntity.ok(new DetailsDataPatient(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.exclude();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsDataPatient> getDetails(@PathVariable Long id) {
        var pantient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsDataPatient(pantient));
    }
}
