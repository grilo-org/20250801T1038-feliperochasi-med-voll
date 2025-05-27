package br.com.feliperochasi.med.voll.api.medic;

import br.com.feliperochasi.med.voll.api.address.Address;
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

    public Medic(RegisterDataMedic data) {
        this.name = data.nome();
        this.email = data.email();
        this.crm = data.crm();
        this.specialised = data.especialidade();
        this.address = new Address(data.endereco());
        this.phone = data.telefone();
    }
}
