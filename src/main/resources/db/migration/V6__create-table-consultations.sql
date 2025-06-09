create table consultations(

    id bigint not null auto_increment,
    medic_id bigint not null,
    patient_id bigint not null,
    date datetime not null,

    primary key(id),
    constraint fk_consultations_medic_id foreign key(medic_id) references medics(id),
    constraint fk_consultations_patient_id foreign key(patient_id) references patients(id)

);