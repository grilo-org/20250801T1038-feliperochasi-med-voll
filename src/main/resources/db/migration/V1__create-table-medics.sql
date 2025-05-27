create table medics(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    specialised varchar(100) not null,
    phone varchar(20) not null,
    address varchar(100) not null,
    neighborhood varchar(100) not null,
    zipcode varchar(9) not null,
    complement varchar(100),
    number varchar(20),
    uf varchar(2) not null,
    city varchar(100) not null,

    primary key(id)
);

