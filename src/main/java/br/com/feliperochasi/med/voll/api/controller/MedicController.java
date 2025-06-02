package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.medic.*;
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
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsDataMedic> register(@RequestBody @Valid RegisterDataMedic dataMedic, UriComponentsBuilder uriBuilder) {
        var medic = new Medic(dataMedic);
        repository.save(medic);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsDataMedic(medic));
    }

    @GetMapping
    public ResponseEntity<Page<ListDataMedic>> listMedics(@PageableDefault(size = 10, sort = {"name"}) Pageable p) {
        var page = repository.findAllByActiveTrue(p).map(ListDataMedic::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailsDataMedic> update(@RequestBody @Valid UpdateDataMedic dataMedic) {
        var medic = repository.getReferenceById(dataMedic.id());
        medic.update(dataMedic);

        return ResponseEntity.ok(new DetailsDataMedic(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsDataMedic> getMedic(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsDataMedic(medic));
    }
}
