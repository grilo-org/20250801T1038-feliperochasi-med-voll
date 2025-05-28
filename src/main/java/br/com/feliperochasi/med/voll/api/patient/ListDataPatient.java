package br.com.feliperochasi.med.voll.api.patient;

public record ListDataPatient(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public ListDataPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
