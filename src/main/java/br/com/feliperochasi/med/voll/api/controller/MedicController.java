package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.medic.ListDataMedic;
import br.com.feliperochasi.med.voll.api.medic.Medic;
import br.com.feliperochasi.med.voll.api.medic.MedicRepository;
import br.com.feliperochasi.med.voll.api.medic.RegisterDataMedic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ListDataMedic> listMedics() {
        return repository.findAll().stream().map(ListDataMedic::new).toList();
    }
}
