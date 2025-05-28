package br.com.feliperochasi.med.voll.api.patient;

public record ListDataPatient(
    String nome,
    String email,
    String cpf
) {
    public ListDataPatient(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
