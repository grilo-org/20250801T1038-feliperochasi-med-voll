package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.medic.Medic;
import br.com.feliperochasi.med.voll.api.medic.MedicRepository;
import br.com.feliperochasi.med.voll.api.medic.RegisterDataMedic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    public void register(@RequestBody RegisterDataMedic dataMedic) {
        repository.save(new Medic(dataMedic));
    }
}
