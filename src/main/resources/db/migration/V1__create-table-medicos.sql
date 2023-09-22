CREATE TABLE medicos(

    id bigint not null auto_increment,
    nombre VARCHAR(100) not null,
    email VARCHAR(100) not null unique,
    documento VARCHAR(6) not null unique,
    especialidad VARCHAR(100) not null,
    calle VARCHAR(100) not null,
    distrito VARCHAR(100) not null,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    ciudad VARCHAR(100) not null,

    PRIMARY KEY(id)

);