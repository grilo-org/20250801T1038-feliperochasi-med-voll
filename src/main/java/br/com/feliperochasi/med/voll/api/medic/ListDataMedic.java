package br.com.feliperochasi.med.voll.api.medic;

public record ListDataMedic(
        String nome,
        String email,
        String crm,
        Specialised especialidade
) {
    public ListDataMedic(Medic medic) {
        this(medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialised());
    }
}
