package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.medic.RegisterDataMedic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @PostMapping
    public void register(@RequestBody RegisterDataMedic dataMedic) {
        System.out.println(dataMedic);
    }
}
