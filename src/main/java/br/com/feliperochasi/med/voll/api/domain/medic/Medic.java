package br.com.feliperochasi.med.voll.api.domain.medic;

import br.com.feliperochasi.med.voll.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medic")
@Table(name = "medics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Specialised specialised;

    @Embedded
    private Address address;

    private Boolean active;

    public Medic(RegisterDataMedic data) {
        this.name = data.nome();
        this.email = data.email();
        this.crm = data.crm();
        this.specialised = data.especialidade();
        this.address = new Address(data.endereco());
        this.phone = data.telefone();
        this.active = true;
    }

    public void update(UpdateDataMedic dataMedic) {
        if (dataMedic.nome() != null) {
            this.name = dataMedic.nome();
        }

        if(dataMedic.telefone() != null) {
            this.phone = dataMedic.telefone();
        }

        if(dataMedic.endereco() != null) {
            this.address.update(dataMedic.endereco());
        }

    }

    public void delete() {
        this.active = false;
    }
}
