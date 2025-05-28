package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.medic.ListDataMedic;
import br.com.feliperochasi.med.voll.api.medic.Medic;
import br.com.feliperochasi.med.voll.api.medic.MedicRepository;
import br.com.feliperochasi.med.voll.api.medic.RegisterDataMedic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDataMedic dataMedic) {
        repository.save(new Medic(dataMedic));
    }

    @GetMapping
    public Page<ListDataMedic> listMedics(@PageableDefault(size = 10, sort = {"name"}) Pageable p) {
        return repository.findAll(p).map(ListDataMedic::new);
    }
}
