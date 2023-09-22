CREATE TABLE pacientes(

    id bigint not null auto_increment,
    nombre VARCHAR(100) not null,
    email VARCHAR(100) not null unique,
    documento VARCHAR(14) not null unique,
    calle VARCHAR(100) not null,
    distrito VARCHAR(100) not null,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    ciudad VARCHAR(100) not null,
    telefono VARCHAR(20) not null,
    activo tinyint not null,

    PRIMARY KEY(id)
);