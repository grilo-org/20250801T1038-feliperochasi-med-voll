package br.com.feliperochasi.med.voll.api.medic;

public record ListDataMedic(
        Long id,
        String nome,
        String email,
        String crm,
        Specialised especialidade
) {
    public ListDataMedic(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialised());
    }
}
